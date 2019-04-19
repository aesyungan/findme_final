package com.example.xl.findnow_final.ResursosApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.entidades.Asignacion;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.entidades.Usuario;
import com.example.logicanegocios.DispositivoLN;
import com.example.logicanegocios.UsuarioLN;
import com.example.xl.findnow_final.MenuPrincipal;

/**
 * Created by XL on 8/1/2017.
 */

public class InicioMovilService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {


            //comprueba si hay un usuario Logueado en la app
            UsuarioLN usuarioLN = new UsuarioLN();
            Usuario usuario= usuarioLN.LocalmostrarUno(context);
            DispositivoLN dispositivoLN = new DispositivoLN();
            //si ya hay usuario registrado carga variable blobales y pasa a otra actividad
            if (usuario!=null){
                Asignacion asignacion= new Asignacion();
                asignacion.setUsuario(usuario);
                asignacion.setDispositivo(dispositivoLN.LocalmostrarUno(context));
                UsuarioSingleton.getInstance().asignacion=asignacion;
                //inicia servicio automaticamente si hay un usuario Logueado
                Log.i("serviceFindMe","Iniciado hay Usuario Logueado");
                Intent pushIntent = new Intent(context, Service_GPS_APP.class);
                context.startService(pushIntent);
            }else {
                Log.i("serviceFindMe","No Iniciado No hay Usuario Logueado");
            }

        }
    }
}
