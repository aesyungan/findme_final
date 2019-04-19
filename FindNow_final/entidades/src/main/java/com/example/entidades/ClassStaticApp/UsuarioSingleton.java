package com.example.entidades.ClassStaticApp;

import com.example.entidades.Asignacion;
import com.example.entidades.Dispositivo;
import com.example.entidades.Rol;
import com.example.entidades.Usuario;

/**
 * Created by XL on 3/1/2017.
 */
public class UsuarioSingleton  {

    public  Usuario usuario;
    public  Asignacion asignacion;
    public Rol rol;
    public Dispositivo dispositivo;


    //region singleton
    private static UsuarioSingleton ourInstance = new UsuarioSingleton();

    public static UsuarioSingleton getInstance() {
        if (ourInstance==null){
            ourInstance=new UsuarioSingleton();
        }
        return ourInstance;
    }

    private UsuarioSingleton() {
    }
    //endregion



}
