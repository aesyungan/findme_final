package com.example.accesodatos;

import android.app.Activity;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by XL on 3/1/2017.
 */

public interface InterfaceApp<T> {
    public void ListarTodo(Activity activity,List<T> list);
    public void ListarID(Activity activity,T item,List<T> list);
    public void Insertar(Activity activity,T item);
    public void Modificar(Activity activity,T item);
    public void Eliminar(Activity activity,T item);

}
