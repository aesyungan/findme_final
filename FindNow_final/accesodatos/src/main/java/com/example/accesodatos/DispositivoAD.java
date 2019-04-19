package com.example.accesodatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.accesodatos.LocalDataBase.AdminSQLiteOpenHelper;
import com.example.entidades.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XL on 5/1/2017.
 */

public class DispositivoAD extends AdminDatos<Dispositivo> implements InterfaceApp<Dispositivo> {


    @Override
    public void ListarTodo(Activity activity, List<Dispositivo> list) {

    }

    @Override
    public void ListarID(Activity activity, Dispositivo item, List<Dispositivo> list) {

    }

    @Override
    public void Insertar(Activity activity, Dispositivo item) {

    }

    @Override
    public void Modificar(Activity activity, Dispositivo item) {


        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_dispositivo",String.valueOf(item.getId_dispositivo())));
        params.add(new BasicNameValuePair("descripcion",item.getDescripcion()));
        params.add(new BasicNameValuePair("imei",item.getImei()));
        params.add(new BasicNameValuePair("fecha_update_registro","2011-1-1"));
        params.add(new BasicNameValuePair("latitud",Double.toString(item.getLatitud())));
        params.add(new BasicNameValuePair("longitud",Double.toString(item.getLongitud())));
        params.add(new BasicNameValuePair("altura",Double.toString(item.getAltura())));
        Log.d("actua","->Datos");
        Log.d("id_dispositivo:->",""+item.getId_dispositivo());
        Log.d("latitud:->",""+item.getLatitud());
        Log.d("longitud:->",""+item.getLongitud());
        Log.d("altura:->",""+item.getAltura());
        new asyncTaskObtenerDatosSinDialog(activity, params, R.string.URL_SERVICE_updateDispositivo).execute();

    }

    public void ModificarConContext(Context context, Dispositivo item) {


        //modificar primero basa datos local
        LocalBorrarAll(context);
        LocalNuevo(context,item);
        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_dispositivo",String.valueOf(item.getId_dispositivo())));
        params.add(new BasicNameValuePair("descripcion",item.getDescripcion()));
        params.add(new BasicNameValuePair("imei",item.getImei()));
        params.add(new BasicNameValuePair("fecha_update_registro","2011-1-1"));
        params.add(new BasicNameValuePair("latitud",Double.toString(item.getLatitud())));
        params.add(new BasicNameValuePair("longitud",Double.toString(item.getLongitud())));
        params.add(new BasicNameValuePair("altura",Double.toString(item.getAltura())));
        Log.d("actua","->Datos");
        Log.d("descripcion:->",""+item.getDescripcion());
        Log.d("imei:->",""+item.getImei());
        Log.d("id_dispositivo:->",""+item.getId_dispositivo());
        Log.d("latitud:->",""+item.getLatitud());
        Log.d("longitud:->",""+item.getLongitud());
        Log.d("altura:->",""+item.getAltura());

        new asyncTaskObtenerDatosSinDialogContext(context,params, R.string.URL_SERVICE_updateDispositivo).execute();

    }

    @Override
    public void Eliminar(Activity activity, Dispositivo item) {

    }

    public void LocalNuevo(Context context,Dispositivo item){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(context,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
       // registro.put("_id",10);
       // registro.put("id_dispositivo",Integer.toString(item.getId_dispositivo()));
       // registro.put("id_dispositivo",Integer.toString(item.getId_dispositivo()));
        registro.put("descripcion",item.getDescripcion());
        registro.put("id_dispositivo",Integer.toString(item.getId_dispositivo()));
        registro.put("imei",item.getImei());
        registro.put("fecha_update_registro",item.getFecha_update_registro());
        registro.put("latitud",Double.toString(item.getLatitud()));
        registro.put("longitud",Double.toString(item.getLongitud()));
        registro.put("altura",Double.toString(item.getAltura()));
        registro.put("fechaupdate",item.getFechaupdate());
        db.insert("dispositivo",null,registro);
        db.close();//cierra la base de datos
        Log.d("dispoRec",Integer.toString(item.getId_dispositivo()));
       Log.i("Ingreso Correcto->","dispositivo");

    }
    public void LocalBorrarAll(Context context){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(context,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        db.execSQL("delete from dispositivo");
        db.close();
        Log.i("BorradoTabla->","dispositivo");

    }

    public Dispositivo LocalmostrarUno(Context context){
        Dispositivo dispositivo= new Dispositivo();
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(context,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();

       // String id_dispositivo=Integer.toString(item.getId_dispositivo());

        //Cursor fila= db.rawQuery("select * from dispositivo where id_dispositivo="+id_dispositivo,null);
        Cursor fila= db.rawQuery("select * from dispositivo",null);
        if (fila.moveToFirst()){
            Log.i("Basedisp",""+fila.toString());
            dispositivo.setId_dispositivo(fila.getInt(1));
            dispositivo.setDescripcion(fila.getString(2));
            dispositivo.setImei(fila.getString(3));
            dispositivo.setFecha_update_registro(fila.getString(4));
            dispositivo.setLatitud(fila.getDouble(5));
            dispositivo.setLongitud(fila.getDouble(6));
            dispositivo.setAltura(fila.getDouble(7));
            dispositivo.setFechaupdate(fila.getString(8));

        }else{
            Log.i("DispositivoEncontrado->","dispositivo");
        }
        db.close();
        return dispositivo;


    }


    @Override
    public List<Dispositivo> mapearDatos(JSONObject jsonReturn) {
        return null;
    }
}
