package com.example.xl.findnow_final;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;


import com.example.entidades.Asignacion;
import com.example.entidades.ClassStaticApp.EstadoTelefono;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.entidades.Dispositivo;
import com.example.entidades.Usuario;
import com.example.logicanegocios.AsignacionLN;

import com.example.xl.findnow_final.ResursosApp.Service_GPS_APP;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Mapa extends Fragment implements OnMapReadyCallback {
    ListView listView_dispositivos;
    private BroadcastReceiver broadcastReceiver;
    public Fragment_Mapa() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if (broadcastReceiver==null){
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    Log.d("recibido",intent.getExtras().get("location").toString());
                }
            };
        }
        EstadoTelefono.activity.registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //se destruye
        if (broadcastReceiver!=null){
            getActivity().unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment__mapa, container, false);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager() .findFragmentById(R.id.mapFragment));
        mapFragment.getMapAsync(this);

        //map type
        FloatingActionButton fab_map_normal = (FloatingActionButton) view.findViewById(R.id.fab_map_normal);
        fab_map_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EstadoTelefono.mMapGlobal.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                Snackbar.make(view, "Vista  Normal", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab_map_hybrid = (FloatingActionButton) view.findViewById(R.id.fab_map_hybrid);
        fab_map_hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EstadoTelefono.mMapGlobal.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                Snackbar.make(view, "Vista Satelite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //tab
        TabHost tabs = (TabHost) view.findViewById(R.id.TabCodigoDispositivo);
        tabs.setup();
        Resources res = getResources();
        TabHost.TabSpec spec = tabs.newTabSpec("Mapa");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", res.getDrawable(R.mipmap.ic_map));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", res.getDrawable(R.drawable.smatphone));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);//por default
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                // Toast.makeText(getContext(), "id" + s, Toast.LENGTH_LONG).show();

                Log.d("id",""+s);
            }
        });

        // para el otro tab la lista de dispositivos
        listView_dispositivos=(ListView)view.findViewById(R.id.Tab_listView_dispositivos);
        AsignacionLN asignacionLN = new AsignacionLN();
        asignacionLN.ListarIDListview(EstadoTelefono.activity,UsuarioSingleton.getInstance().asignacion,listView_dispositivos);//maadmos lst para que se llene datos

        //ejecuta el asinctask para el optener datos
       // new OptenerPuntosGeograficosTab2(listView_dispositivos).execute();

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
      //  mMap = googleMap;
        EstadoTelefono.mMapGlobal = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

       // mMap.setMyLocationEnabled(true);
        EstadoTelefono.mMapGlobal.setMyLocationEnabled(true);
        //determina la ubicacion

      EstadoTelefono.activity.startService(new Intent(EstadoTelefono.activity,Service_GPS_APP.class));
       //    miUbicacion();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
