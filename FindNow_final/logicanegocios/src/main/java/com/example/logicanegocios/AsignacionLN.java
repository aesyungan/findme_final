package com.example.logicanegocios;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;


import com.example.accesodatos.AsignacionAD;
import com.example.entidades.Asignacion;
import com.example.entidades.Dispositivo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by XL on 5/1/2017.
 */

public class AsignacionLN {
    public void ListarID(Activity activity, Asignacion item, List<Asignacion> list) {

        AsignacionAD asignacionAD =new AsignacionAD();
        asignacionAD.ListarID(activity,item,list);
    }

    public void AgregarMarcador(Activity activity, Asignacion item, List<Asignacion> list,GoogleMap mMap){
        AsignacionAD asignacionAD =new AsignacionAD();
        asignacionAD.AgregarMarcador(activity,item,list,mMap);

    }

    public void AgregarMarcadorV2(Context context, Asignacion item){
        AsignacionAD asignacionAD =new AsignacionAD();
        asignacionAD.AgregarMarcadorV2(context,item);

    }
    public void ListarIDListview(Activity activity, Asignacion item, final ListView listView) {
        AsignacionAD asignacionAD =new AsignacionAD();
        asignacionAD.ListarIDListview(activity,item,listView);
    }
    public void Insertar(Activity activity, Asignacion item){
        AsignacionAD asignacionAD =new AsignacionAD();
        asignacionAD.Insertar(activity,item);
    }
}
