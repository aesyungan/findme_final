package com.example.logicanegocios;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.example.accesodatos.DispositivoAD;
import com.example.accesodatos.UsuarioAD;
import com.example.entidades.Dispositivo;
import com.example.entidades.Usuario;


/**
 * Created by XL on 11/12/2016.
 */

public class UsuarioLN {
    public UsuarioLN() {
    }

    public void   verificarLogueo(final Usuario user,  final Activity activityActual,final Class classGo){
        UsuarioAD usuarioAD =new UsuarioAD();
         usuarioAD.verificarLogueo2( user, activityActual, classGo);
    }
    public void DescargarImagen(ImageView imageView, String urlImagen){
        UsuarioAD usuarioAD =new UsuarioAD();
        usuarioAD.DescargarImagen( imageView, urlImagen);
    }

    //LOCAL DATA BASE

    public void LocalNuevo(Context context,Usuario item){
        UsuarioAD usuarioAD =new UsuarioAD();
        usuarioAD.LocalNuevo( context,item);
    }
    public void LocalBorrarAll(Context context){
        UsuarioAD usuarioAD =new UsuarioAD();
        usuarioAD.LocalBorrarAll(context);
    }

    public Usuario LocalmostrarUno(Context context){
        UsuarioAD usuarioAD =new UsuarioAD();
     return    usuarioAD.LocalmostrarUno(context);
    }

    public void InsertarTodo(Activity activity, Usuario itemUsuario){
        UsuarioAD usuarioAD =new UsuarioAD();
            usuarioAD.InsertarTodo(activity,itemUsuario);
    }

}
