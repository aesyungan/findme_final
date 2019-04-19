package com.example.accesodatos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accesodatos.Adaptadores.AdapterListaUsuarioDispositivo;
import com.example.entidades.Asignacion;
import com.example.entidades.ClassStaticApp.EstadoTelefono;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.entidades.Dispositivo;
import com.example.entidades.Rol;
import com.example.entidades.Usuario;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by XL on 4/1/2017.
 */

public  class AsignacionAD extends AdminDatos<Asignacion> implements InterfaceApp<Asignacion> {

    public void AgregarMarcador(Activity activity, Asignacion item, List<Asignacion> list,GoogleMap mMap){

        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_usuario",String.valueOf(item.getUsuario().getId_usuario())));
        Log.e("param->",""+params.toString());
        new asyncTaskObtenerDatosListMap(activity,params,R.string.URL_SERVICE_optenerAsignacionIdUsuario,list,mMap) {
            @Override
            public void ingresarMarcador(List<Asignacion> list, GoogleMap mMap) {

               mMap.clear();//borra los datos de la mapa
                Log.d("numero Datos", ":" + list.size());
                if (list.size()>0){
                    for (int i = 0; i < list.size(); i++) {

                        Dispositivo dato = list.get(i).getDispositivo();
                        LatLng sydney1 = new LatLng(dato.getLatitud(), dato.getLongitud());
                        mMap.addMarker(new MarkerOptions().position(sydney1).title(dato.getDescripcion()));
                    }
                }


            }
        }.execute();
    }

    public void AgregarMarcadorV2(Context context, Asignacion item){

        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_usuario",String.valueOf(item.getUsuario().getId_usuario())));
        Log.e("param->",""+params.toString());
        new asyncTaskObtenerDatosListMapV2(context,params,R.string.URL_SERVICE_optenerAsignacionIdUsuario) {
            @Override
            public void ingresarMarcador(List<Asignacion> list) {



                //solo ejecuta si esta abierto la actividad
                if (list.size() > 0) {
                if (EstadoTelefono.activity!=null) {
                    if (EstadoTelefono.mMapGlobal!=null){
                        EstadoTelefono.mMapGlobal.clear();//borra los datos de la mapa
                        Log.d("numero Datos", ":" + list.size());

                            for (int i = 0; i < list.size(); i++) {

                                Dispositivo dato = list.get(i).getDispositivo();
                                LatLng sydney1 = new LatLng(dato.getLatitud(), dato.getLongitud());
                                EstadoTelefono.mMapGlobal.addMarker(new MarkerOptions().position(sydney1).title(dato.getDescripcion()));
                            }
                        }
                    }


                }


            }
        }.execute();
    }

    public void ListarIDListview(Activity activity, Asignacion item, final ListView listView) {
        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_usuario",Integer.toString(item.getUsuario().getId_usuario())));


        //log
        Log.i("Cargando datos ..","LstDispositivos");
        new asyncTaskObtenerDatosListView(activity, params, R.string.URL_SERVICE_optenerAsignacionIdUsuario) {
            @Override
            public void AdaptarDatosEnListView(List<Asignacion> lstdatos) {


                AdapterListaUsuarioDispositivo adapter = new AdapterListaUsuarioDispositivo(EstadoTelefono.activity.getApplicationContext(),lstdatos);
                listView.setAdapter(adapter);
            }
        }.execute();

    }
    @Override
    public void ListarTodo(Activity activity, List<Asignacion> list) {


    }

    @Override
    public void ListarID(Activity activity, Asignacion item, List<Asignacion> list) {
        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_usuario",Integer.toString(item.getUsuario().getId_usuario())));


        //log
        Log.i("Cargando datos ..","LstDispositivos");
        new asyncTaskObtenerDatosList(activity,params,R.string.URL_SERVICE_optenerAsignacionIdUsuario,list).execute();

    }

    @Override
    public void Insertar(Activity activity, Asignacion item) {

        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_usuario", Integer.toString(item.getUsuario().getId_usuario())));
        params.add(new BasicNameValuePair("id_dispositivo", Integer.toString(item.getDispositivo().getId_dispositivo())));
        params.add(new BasicNameValuePair("propietario", "0"));
        String REGISTER_URL = activity.getApplicationContext().getString(R.string.Agregar_Asignacion);
        new RegistroAsyncTask(activity,params,REGISTER_URL).execute();
    }

    @Override
    public void Modificar(Activity activity, Asignacion item) {

    }

    @Override
    public void Eliminar(Activity activity, Asignacion item) {

    }


    @Override
    public List<Asignacion> mapearDatos(JSONObject json) {
        List<Asignacion> items=new ArrayList<>();
        try {


            JSONArray products = json.getJSONArray("listDatos");
            for (int i = 0; i < products.length(); i++) {
                JSONObject todo = products.getJSONObject(i);
                Log.e("todoDatos",""+todo.toString());
                Asignacion asignacion = new Asignacion();
                asignacion.setId_asignacion(todo.getInt("id_asignacion"));

                JSONObject c = todo.getJSONObject("usuario_");
                Log.d("usuario ->!", c.toString());
                Usuario usuario = new Usuario();
                usuario.setId_usuario(Integer.parseInt(c.getString("id_usuario").toString()));
                JSONObject r = c.getJSONObject("rol");
                Log.d("Rol ->!", r.toString());
                Rol rol = new Rol();
                rol.setId_rol(Integer.parseInt(r.getString(("id_rol").toString())));
                rol.setEstado(Integer.parseInt(r.getString("estado").toString()));
                rol.setDescripcion(r.getString("descripcion_rol").toString());
                usuario.setRol(rol);
                usuario.setNombres(c.getString("nombres").toString());
                usuario.setApellidos(c.getString("apellidos").toString());
                usuario.setCedula(c.getString("cedula").toString());
                usuario.setFoto(c.getString("foto").toString());
                usuario.setUsuario(c.getString("usuario").toString());
                usuario.setContrasena(c.getString("contrasena").toString());
                usuario.setEstado(Integer.parseInt(c.getString("estado").toString()));
                usuario.setEdad(Integer.parseInt(c.getString("edad").toString()));
                usuario.setDireccion(c.getString("direccion").toString());

                Dispositivo dispositivo = new Dispositivo();
                JSONObject dispo = todo.getJSONObject("dispositivo");
                Log.d("Dispositivo ->!", dispo.toString());
                dispositivo.setId_dispositivo(dispo.getInt("id_dispositivo"));
                dispositivo.setDescripcion(dispo.getString("descripcion"));
                dispositivo.setImei(dispo.getString("imei"));
                dispositivo.setFecha_update_registro(dispo.getString("fecha_update_registro"));
                dispositivo.setLatitud(dispo.getDouble("latitud"));
                dispositivo.setLongitud(dispo.getDouble("longitud"));
                dispositivo.setAltura(dispo.getDouble("altura"));
                dispositivo.setFechaupdate(dispo.getString("fechaupdate"));
                asignacion.setPropietario(todo.getInt("propietario"));
                asignacion.setUsuario(usuario);
                asignacion.setDispositivo(dispositivo);

                items.add(asignacion);
            }

        }catch (JSONException ex){
           Log.e("Error=",""+ex.getMessage());
        }
        return items;
    }
}
