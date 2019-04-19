package com.example.xl.findnow_final.ResursosApp;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.entidades.Asignacion;
import com.example.entidades.ClassStaticApp.EstadoTelefono;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.entidades.Dispositivo;
import com.example.entidades.Usuario;
import com.example.logicanegocios.AsignacionLN;
import com.example.logicanegocios.DispositivoLN;
import com.example.logicanegocios.UsuarioLN;
import com.example.xl.findnow_final.R;

public class Service_GPS_APP extends Service {
    private Asignacion asignacion;
    private Context context;
    private View view ;
    private Resources resources;
    private Activity activity;

    private LocationListener listener;
    private LocationManager locationManager;
    public Service_GPS_APP() {
        this.context = EstadoTelefono.context;
        this.view = EstadoTelefono.view;
        this.resources = EstadoTelefono.resources;
        this.activity = EstadoTelefono.activity;
        this.asignacion = UsuarioSingleton.getInstance().asignacion;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("OncreateService->","Service_GPS_APP");
        MiUbicacion();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //cuando se elimina el servicio
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(listener);
        }
    }

    private void MiUbicacion(){

        listener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {

                new Thread(new Runnable() {
                    public void run() {
                        //Aquí ejecutamos nuestras tareas costosas
                actualizarUbicacion(location);
               Intent i = new Intent("location_update");
                i.putExtra("location",location);
                sendBroadcast(i);
                        //fin

                    }
                }).start();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

                Log.d("GPS","Activo");
                //si app esta abierto
                if (EstadoTelefono.activity!=null) {
                    Snackbar.make(EstadoTelefono.view, "GPS Activado", Snackbar.LENGTH_LONG)
                            .setAction("Desactivar", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //  EstadoTelefono.turnGPSOff(EstadoTelefono.context);
                                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                    //Toast.makeText(EstadoTelefono.context, "Gps Disable !", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                }
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("GPS","Inactivo");
                //si app esta abierto
                if (EstadoTelefono.activity!=null) {
                    Snackbar snackbar = Snackbar.make(EstadoTelefono.view, "Gps Desactivado", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Activar", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //activa gps
                                    // EstadoTelefono.turnGPSOn(EstadoTelefono.context);
                                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                    // Toast.makeText(EstadoTelefono.context, "GPS Enable !", Toast.LENGTH_SHORT).show();
                                }
                            });

                    //ACTION
                    snackbar.setActionTextColor(EstadoTelefono.resources.getColor(R.color.celeste));
                    View snackBarView = snackbar.getView();
                    //BACKGROUND
                    snackBarView.setBackgroundColor(EstadoTelefono.resources.getColor(R.color.rojo));
                    //MESSAGE
                    TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(EstadoTelefono.resources.getColor(R.color.blanco));
                    tv.setTextSize(20);

                    snackbar.show();
                }
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //se actualiza cada 2.5 segundos
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 2000, 0, listener);
    }
    private void actualizarUbicacion( Location location) {


        if (EstadoTelefono.estadoConneccion(getApplicationContext())) {
            Log.e("Actualizando..","Datos");
            //agregar al marcador
            AsignacionLN asignacionLN= new AsignacionLN();
            // asignacionLN.AgregarMarcador(EstadoTelefono.activity,UsuarioSingleton.getInstance().asignacion,itemsMapa,mMap); //optiene todos los puntos geograficos

            UsuarioLN usuarioLN = new UsuarioLN();
            DispositivoLN dispositivoLN = new DispositivoLN();
            Usuario usuario = usuarioLN.LocalmostrarUno(getApplicationContext());//recupera user logueado de database local

            Dispositivo dispositivo = dispositivoLN.LocalmostrarUno(getApplicationContext());
          Log.w("idr_dispositivo",Integer.toString(dispositivo.getId_dispositivo()));
            Log.d("localUser->",""+usuario.getId_usuario());
            Asignacion asignacion = new Asignacion();
            asignacion.setUsuario(usuario);
            asignacion.setDispositivo(dispositivo);

            if(EstadoTelefono.activity!=null) {//ejecuta solo si esta abierta la app
                asignacionLN.AgregarMarcadorV2(getApplicationContext(), asignacion);
            }
            if (EstadoTelefono.GpsActivo(getApplicationContext())) {




                if (location != null) {
                    // listaGps.clear(); //borra l lista de elementos  de las ubicaciones temporalles
                    //mMap.clear();//borra los datos de la mapa
                    //muestra mi ubicacion

                    //Dispositivo dispositivo = this.asignacion.getDispositivo();

                   // dispositivo.setId_dispositivo(1);
                    dispositivo.setLatitud(location.getLatitude());
                    dispositivo.setLongitud(location.getLongitude());
                    dispositivo.setAltura(location.getAltitude());

                    //log de datos
                    //actualiza a la nube la posicion
                    dispositivoLN.ModificarConContext(getApplicationContext(),dispositivo);
                    // agregarMarcador(lat, lng);
                }

            }  else {
                //solo si esta abierto la app muestra
                if (EstadoTelefono.activity!=null) {
                    Snackbar.make(EstadoTelefono.view, "GPS Inactivo", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

        }else{


            if (EstadoTelefono.activity!=null) {
                Snackbar.make(EstadoTelefono.view, "Su Connecion a Internet es Lenta...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }




    }

}
