package com.example.logicanegocios;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.accesodatos.DispositivoAD;
import com.example.entidades.Dispositivo;

/**
 * Created by XL on 5/1/2017.
 */

public class DispositivoLN {
    public void Modificar(Activity activity, Dispositivo item) {
        DispositivoAD dispositivoAD = new DispositivoAD();

        dispositivoAD.Modificar(activity,item);
    }

    public void ModificarConContext(Context context, Dispositivo item)
    {
        DispositivoAD dispositivoAD = new DispositivoAD();

        dispositivoAD.ModificarConContext(context,item);
    }
    public void LocalNuevo(Context context,Dispositivo item){
        DispositivoAD dispositivoAD = new DispositivoAD();

        dispositivoAD.LocalNuevo(context,item);
    }
    public void LocalBorrarAll(Context context){
        DispositivoAD dispositivoAD = new DispositivoAD();

        dispositivoAD.LocalBorrarAll(context);
    }

    public Dispositivo LocalmostrarUno(Context context){
        DispositivoAD dispositivoAD = new DispositivoAD();

      return   dispositivoAD.LocalmostrarUno(context);
    }
}
