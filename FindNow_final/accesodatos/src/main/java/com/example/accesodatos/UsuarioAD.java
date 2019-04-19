package com.example.accesodatos;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.accesodatos.LocalDataBase.AdminSQLiteOpenHelper;
import com.example.entidades.Asignacion;
import com.example.entidades.ClassStaticApp.EstadoTelefono;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.entidades.Dispositivo;
import com.example.entidades.Rol;
import com.example.entidades.Usuario;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by XL on 11/12/2016.
 */

public class UsuarioAD extends AdminDatos<Usuario> implements InterfaceApp<Usuario> {
    public UsuarioAD() {
    }
    //verifica el Login
    public void verificarLogueo2(final Usuario user,final Activity activityActual, final Class classGo)  {


        List params = new ArrayList();
        params.add(new BasicNameValuePair("usuario",user.getUsuario()));
        params.add(new BasicNameValuePair("password",user.getContrasena()));


       new asyncTaskObtenerDatos(activityActual,params,R.string.URL_SERVICE_VERIFICAR_LOGUEO,"Verificando..") {
           @Override
           public void realizarAlgo(JSONObject json,Usuario usuario1,Dispositivo dispositivo1) {
               try {
                   int success=0;
                   success = json.getInt("success");
                   if (success==1) {

                       JSONArray products = json.getJSONArray("listDatos");
                       for (int i = 0; i < products.length(); i++) {
                           JSONObject todo = products.getJSONObject(i);
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
                           UsuarioSingleton.getInstance().asignacion=asignacion;
                           //guarda datos en local data base
                           UsuarioAD usuarioAD = new UsuarioAD();
                            usuarioAD.LocalBorrarAll(activityActual.getApplicationContext());//borra usuario anterior
                           usuarioAD.LocalNuevo(activityActual.getApplicationContext(),usuario);
                           DispositivoAD dispositivoAD = new DispositivoAD();
                           dispositivoAD.LocalBorrarAll(activityActual.getApplicationContext());//borra dispositivo anterior
                           Log.d("id_recuD",Integer.toString(dispositivo.getId_dispositivo()));
                           dispositivoAD.LocalNuevo(activityActual.getApplicationContext(),dispositivo);

                       }


                   }
               }catch (JSONException ex){
                   Toast.makeText(activityActual.getApplicationContext(),"Error="+ex.getMessage(),Toast.LENGTH_LONG).show();
               }
               //finaliza codigo abstract
           }

           @Override
           public void guardarUserLocalDATABASE(Usuario usuario, Dispositivo dispositivo, Context context) {


               pDialog.dismiss();

               activityActual.finish();
               //Intent i = new Intent(activity.getApplicationContext(), activity.getClass());
               Intent i = new Intent(activityActual, classGo);
               activityActual.startActivity(i);
           }
       }.execute();

        Log.d("todo","salio bn");



    }
    //descarga la imagen del usuario
    public void DescargarImagen(ImageView imageView,String urlImagen){
        new DownloadImageTask(imageView).execute(urlImagen);
    }


    @Override
    public void ListarTodo(Activity activity, List<Usuario> list) {

    }

    @Override
    public void ListarID(Activity activity, Usuario item, List<Usuario> list) {

    }

    @Override
    public void Insertar(Activity activity, Usuario item) {

    }
    //retorna el nombre del dispositivo
    public String getPhoneName() {
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = myDevice.getName();
        return deviceName;
    }
    //retorna el imei de telefono
    public static String getImei(Context c) {
        TelephonyManager telephonyManager = (TelephonyManager) c
                .getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyManager.getDeviceId();
    }

    public void InsertarTodo(Activity activity, Usuario itemUsuario) {
        List params = new ArrayList();
        params.add(new BasicNameValuePair("id_rol", "2"));
        params.add(new BasicNameValuePair("nombres", itemUsuario.getNombres()));
        params.add(new BasicNameValuePair("apellidos", itemUsuario.getApellidos()));
        params.add(new BasicNameValuePair("cedula", itemUsuario.getCedula()));
        //params.add(new BasicNameValuePair("foto", itemUsuario.getFoto()));
        params.add(new BasicNameValuePair("foto", "https://marinersoftware.com/wp-content/pmedia/img-user-300x300.jpg"));
        params.add(new BasicNameValuePair("usuario", itemUsuario.getUsuario()));
        params.add(new BasicNameValuePair("contrasena", itemUsuario.getContrasena()));
        //params.add(new BasicNameValuePair("estado", Integer.toString(itemUsuario.getEstado())));
        params.add(new BasicNameValuePair("estado", "1"));//activo
        params.add(new BasicNameValuePair("edad", Integer.toString(itemUsuario.getEdad())));
        params.add(new BasicNameValuePair("direccion", itemUsuario.getDireccion()));

        params.add(new BasicNameValuePair("descripcion", getPhoneName()));//nombre del telefono
        params.add(new BasicNameValuePair("imei", getImei(activity.getApplicationContext())));//imei del telefono
        String REGISTER_URL = activity.getApplicationContext().getString(R.string.Registro_Usuario);
        new RegistroAsyncTask(activity,params,REGISTER_URL).execute();
    }

    @Override
    public void Modificar(Activity activity, Usuario item) {

    }

    @Override
    public void Eliminar(Activity activity, Usuario item) {

    }


    public void LocalNuevo(Context context,Usuario item){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(context,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
       // registro.put("_id",1);
        registro.put("id_usuario",Integer.toString(item.getId_usuario()));
        registro.put("id_rol",Integer.toString(item.getRol().getId_rol()));
        registro.put("nombres",item.getNombres());
        registro.put("apellidos",item.getApellidos());
        registro.put("cedula",item.getCedula());
        registro.put("foto",item.getFoto());
        registro.put("usuario",item.getUsuario());
        registro.put("contrasena",item.getContrasena());
        registro.put("estado",item.getEstado());
        registro.put("edad",item.getEdad());
        registro.put("direccion",item.getDireccion());

        db.insert("usuario",null,registro);
        db.close();//cierra la base de datos
        Log.i("Ingreso Correcto->","usuario");

    }
    public void LocalBorrarAll(Context context){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(context,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        db.execSQL("delete from  usuario");
        db.close();
        Log.i("BorradoTabla->","usuario");

    }

    public Usuario LocalmostrarUno(Context context){
        Usuario usuario=null;
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(context,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();

        //String id_usuario=Integer.toString(item.getId_usuario());

       // Cursor fila= db.rawQuery("select * from usuario where id_usuario="+id_usuario,null);
        Cursor fila= db.rawQuery("select * from usuario",null);
        if (fila.moveToFirst()){
            //1
            Log.i("Baseuser",""+fila.toString());
           usuario= new Usuario();
            usuario.setId_usuario(fila.getInt(1));
            Rol rol = new Rol();
            rol.setId_rol(fila.getInt(2));
            usuario.setRol(rol);
            usuario.setNombres(fila.getString(3));
            usuario.setApellidos(fila.getString(4));
            usuario.setCedula(fila.getString(5));
            usuario.setFoto(fila.getString(6));
            usuario.setUsuario(fila.getString(7));
            usuario.setContrasena(fila.getString(8));
            usuario.setEstado(fila.getInt(9));
            usuario.setEdad(fila.getInt(10));
            usuario.setDireccion(fila.getString(11));

        }else{
            Log.i("UsuarioEncontrado->","usuario");
        }
        db.close();
        return usuario;


    }
    @Override
    public List<Usuario> mapearDatos(JSONObject jsonReturn) {
        return null;
    }


}
