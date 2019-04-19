package com.example.accesodatos.LocalDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by XL on 7/1/2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "administracion", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("Creando","Data_Base");
        sqLiteDatabase.execSQL("create table dispositivo( _id1 integer PRIMARY KEY AUTOINCREMENT,id_dispositivo int ,descripcion text,imei text,fecha_update_registro text,latitud text,longitud text,altura text,fechaupdate text)");
        sqLiteDatabase.execSQL("create table usuario(_id integer PRIMARY KEY AUTOINCREMENT,id_usuario int ,id_rol int,nombres text,apellidos text,cedula text,foto text,usuario text,contrasena text,estado int,edad int,direccion text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("delete from dispositivo");
        sqLiteDatabase.execSQL("delete from usuario");
    }
}
