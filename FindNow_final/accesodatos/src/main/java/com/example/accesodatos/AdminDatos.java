package com.example.accesodatos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.entidades.Dispositivo;
import com.example.entidades.JSON;
import com.example.entidades.Usuario;
import com.google.android.gms.maps.GoogleMap;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XL on 3/1/2017.
 */

public abstract class AdminDatos<T> {
    //para realizar cualquier peticon con prosesDialog
        abstract class asyncTaskObtenerDatos extends AsyncTask<String, String, String> {
            //atributos
            int successresp=0;
            private Activity activityActual;
            private List params;
            private int URL_SERVICE_WEB;
            private String msgMostrarDialog;
            public Usuario usuarioL;
            public Dispositivo dispositivoL;

            public ProgressDialog pDialog;
            JSON jsonS = new JSON();
            String message="error Coneccion";//msj por default
            //metodo abstracto que realiza instrucciones q queremos
            public abstract void realizarAlgo(JSONObject jsonReturn,Usuario usuario,Dispositivo dispositivo);
            public abstract void guardarUserLocalDATABASE(Usuario usuario, Dispositivo dispositivo,Context context);


            //costructor


            public asyncTaskObtenerDatos(Activity activityActual, List params, int URL_SERVICE_WEB, String msgMostrarDialog) {
                this.activityActual = activityActual;
                this.params = params;
                this.URL_SERVICE_WEB = URL_SERVICE_WEB;
                this.msgMostrarDialog = msgMostrarDialog;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(activityActual);
                pDialog.setMessage(msgMostrarDialog);
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... args) {

                try {
                    // Building Parameters
                    /*List params = new ArrayList();
                    params.add(new BasicNameValuePair("usuario",user.getUsuario()));
                    params.add(new BasicNameValuePair("password",user.getContrasena()));
                    */
                    //Posting user data to script

                    JSONObject jsonObject = (this.jsonS.makeHttpRequest(activityActual.getApplicationContext().getString(URL_SERVICE_WEB), "GET", params));
                    message= jsonObject.getString("message").toString();

                    successresp = jsonObject.getInt("success");
                    if (successresp == 1) {
                        realizarAlgo(jsonObject,usuarioL,dispositivoL);


                    }




                }
                catch (JSONException e) {
                    Log.e("errorAdminDatos=",""+e.getMessage());
                }

                return message;
            }

            protected void onPostExecute(String mensage) {
                pDialog.dismiss();
                if (mensage!= null) {
                    Toast.makeText(activityActual, mensage, Toast.LENGTH_LONG).show();
                }
                if (successresp==1){
                guardarUserLocalDATABASE(usuarioL,dispositivoL,this.activityActual);
                }
            }




        }
    //descargar imagenes desde internet para ImageView
        class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            //pd.show();
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //pd.dismiss();
            bmImage.setImageBitmap(result);
        }
    }


// llenar datos en una lista
  class asyncTaskObtenerDatosList extends AsyncTask<String, String, String> {
    //atributos
    private Activity activityActual;
    private List params;
    private int URL_SERVICE_WEB;
    private List<T> lstdatos;
    JSON jsonS = new JSON();
    String message="error Coneccion";//msj por default
    //costructor
    public asyncTaskObtenerDatosList(Activity activityActual, List params, int URL_SERVICE_WEB, List<T> lstdatos) {
        this.activityActual = activityActual;
        this.params = params;
        this.URL_SERVICE_WEB = URL_SERVICE_WEB;
        this.lstdatos =lstdatos;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... args) {

        try {
            // Building Parameters
                    /*List params = new ArrayList();
                    params.add(new BasicNameValuePair("usuario",user.getUsuario()));
                    params.add(new BasicNameValuePair("password",user.getContrasena()));
                    */
            //Posting user data to script

            JSONObject jsonObject = (this.jsonS.makeHttpRequest(activityActual.getApplicationContext().getString(URL_SERVICE_WEB), "GET", params));
            message= jsonObject.getString("message").toString();

            int successresp=0;
            successresp = jsonObject.getInt("success");
            if (successresp == 1) {
                Log.d("list2 realizado!", jsonObject.toString());
               this.lstdatos= mapearDatos(jsonObject);
            }
        }
        catch (JSONException e) {
            Log.e("errorAdminDatos=",""+e.getMessage());
        }

        return message;
    }

    protected void onPostExecute(String mensage) {

        if (mensage!= null) {
            Log.i("respuestaAsingTask",mensage);
        }
    }
}

    // llenar datos en una listaView
    abstract class asyncTaskObtenerDatosListView extends AsyncTask<String, String, String> {
        //atributos

        private Activity activityActual;
        private List params;
        private int URL_SERVICE_WEB;
        private List<T> lstdatos= new ArrayList<>();
       abstract public void  AdaptarDatosEnListView(List<T> lstdatos);
        JSON jsonS = new JSON();
        String message="error Coneccion";//msj por default
        //costructor
        public asyncTaskObtenerDatosListView(Activity activityActual, List params, int URL_SERVICE_WEB ) {
            this.activityActual = activityActual;
            this.params = params;
            this.URL_SERVICE_WEB = URL_SERVICE_WEB;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {

            try {
                // Building Parameters
                    /*List params = new ArrayList();
                    params.add(new BasicNameValuePair("usuario",user.getUsuario()));
                    params.add(new BasicNameValuePair("password",user.getContrasena()));
                    */
                //Posting user data to script

                JSONObject jsonObject = (this.jsonS.makeHttpRequest(activityActual.getApplicationContext().getString(URL_SERVICE_WEB), "GET", params));
                message= jsonObject.getString("message").toString();

                int successresp=0;
                successresp = jsonObject.getInt("success");
                if (successresp == 1) {
                    Log.d("list2 realizado!", jsonObject.toString());
                    this.lstdatos= mapearDatos(jsonObject);
                }
            }
            catch (JSONException e) {
                Log.e("errorAdminDatos=",""+e.getMessage());
            }

            return message;
        }

        protected void onPostExecute(String mensage) {
            AdaptarDatosEnListView(this.lstdatos);

            if (mensage!= null) {
                Log.i("respuestaAsingTask",mensage);
            }
        }
    }

    //fin

    abstract   class asyncTaskObtenerDatosListMap extends AsyncTask<String, String, String> {
        //atributos
        private GoogleMap mMap;
        private Activity activityActual;
        private List params;
        private int URL_SERVICE_WEB;
        private List<T> lstdatos;
        JSON jsonS = new JSON();
        String message="error Coneccion";//msj por default
        //metodo abstracto
        public abstract void ingresarMarcador(List<T> items,GoogleMap mMap);
        //costructor
        public asyncTaskObtenerDatosListMap(Activity activityActual, List params, int URL_SERVICE_WEB, List<T> lstdatos,GoogleMap mMap) {
            this.activityActual = activityActual;
            this.params = params;
            this.URL_SERVICE_WEB = URL_SERVICE_WEB;
            this.lstdatos =lstdatos;
            this.mMap=mMap;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {

            try {
                // Building Parameters
                    /*List params = new ArrayList();
                    params.add(new BasicNameValuePair("usuario",user.getUsuario()));
                    params.add(new BasicNameValuePair("password",user.getContrasena()));
                    */
                //Posting user data to script

                JSONObject jsonObject = (this.jsonS.makeHttpRequest(activityActual.getApplicationContext().getString(URL_SERVICE_WEB), "GET", params));
                message= jsonObject.getString("message").toString();

                int successresp=0;
                successresp = jsonObject.getInt("success");
                if (successresp == 1) {
                    Log.d("list realizado!", jsonObject.toString());
                    this.lstdatos= mapearDatos(jsonObject);


                }
            }
            catch (JSONException e) {
                Log.e("errorAdminDatos=",""+e.getMessage());
            }

            return message;
        }

        protected void onPostExecute(String mensage) {

            ingresarMarcador(lstdatos,mMap);
            if (mensage!= null) {
                Log.i("respuestaAsingTask",mensage);
            }
        }
    }
    //metodo abstracto que realiza instrucciones q queremos
    public abstract List<T> mapearDatos(JSONObject jsonReturn);
    //para realizar cualquier peticon con prosesDialog
    class asyncTaskObtenerDatosSinDialog extends AsyncTask<String, String, String> {
        //atributos

        private Activity activityActual;
        private List params;
        private int URL_SERVICE_WEB;
        private String message="error";
        JSON jsonSinDialog = new JSON();
        //metodo abstracto que realiza instrucciones q queremos

        public asyncTaskObtenerDatosSinDialog(Activity activityActual,List params, int URL_SERVICE_WEB) {
            this.activityActual = activityActual;
            this.params = params;
            this.URL_SERVICE_WEB = URL_SERVICE_WEB;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... args) {
            try{
                JSONObject jsonObj = jsonSinDialog.makeHttpRequest(activityActual.getApplicationContext().getString(URL_SERVICE_WEB), "GET", params);
                message=jsonObj.getString("message").toString();
                Log.e("AdminDatos=",""+jsonObj.toString());


            }
            catch (JSONException e) {
                Log.e("errorAdminDatos=",""+e.getMessage());
            }
            return message;
        }

        protected void onPostExecute(String mensage) {

            if (mensage!= null) {
                Log.w("respuestaAsingTask",mensage);
            }
        }
    }




    class asyncTaskObtenerDatosSinDialogContext extends AsyncTask<String, String, String> {
        //atributos

        private Context context;
        private List params;
        private int URL_SERVICE_WEB;
        private String message = "error";
        JSON jsonSinDialog = new JSON();
        //metodo abstracto que realiza instrucciones q queremos

        public asyncTaskObtenerDatosSinDialogContext(Context context, List params, int URL_SERVICE_WEB) {
            this.context = context;
            this.params = params;
            this.URL_SERVICE_WEB = URL_SERVICE_WEB;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                JSONObject jsonObj = jsonSinDialog.makeHttpRequest(context.getString(URL_SERVICE_WEB), "GET", params);
                message = jsonObj.getString("message").toString();
                Log.e("AdminDatos=", "" + jsonObj.toString());


            } catch (JSONException e) {
                Log.e("errorAdminDatos=", "" + e.getMessage());
            }
            return message;
        }

        protected void onPostExecute(String mensage) {

            if (mensage != null) {

                Log.w("respuestaAsingTask", mensage);
            }
        }
    }


    abstract   class asyncTaskObtenerDatosListMapV2 extends AsyncTask<String, String, String> {
        //atributos
        private Context context;
        private List params;
        private int URL_SERVICE_WEB;
        private List<T> lstdatos;
        JSON jsonS = new JSON();
        String message="error Coneccion";//msj por default
        //metodo abstracto
        public abstract void ingresarMarcador(List<T> items);
        //costructor
        public asyncTaskObtenerDatosListMapV2(Context context, List params, int URL_SERVICE_WEB) {
            this.context = context;
            this.params = params;
            this.URL_SERVICE_WEB = URL_SERVICE_WEB;
            this.lstdatos = new ArrayList<>();

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {

            try {
                // Building Parameters
                    /*List params = new ArrayList();
                    params.add(new BasicNameValuePair("usuario",user.getUsuario()));
                    params.add(new BasicNameValuePair("password",user.getContrasena()));
                    */
                //Posting user data to script

                JSONObject jsonObject = (this.jsonS.makeHttpRequest(context.getString(URL_SERVICE_WEB), "GET", params));
                message= jsonObject.getString("message").toString();

                int successresp=0;
                successresp = jsonObject.getInt("success");
                if (successresp == 1) {
                    Log.d("list realizado!", jsonObject.toString());
                    this.lstdatos= mapearDatos(jsonObject);


                }
            }
            catch (JSONException e) {
                Log.e("errorAdminDatos=",""+e.getMessage());
            }

            return message;
        }

        protected void onPostExecute(String mensage) {

            ingresarMarcador(this.lstdatos);
            if (mensage!= null) {
                Log.i("respuestaAsingTask",mensage);
            }
        }
    }


    ///ingreso

   public class RegistroAsyncTask extends AsyncTask<String, String, String> {
        JSON json = new JSON();
        private ProgressDialog pDialog;
       Activity activityActual;
       List params;
       String REGISTER_URL="";
       public RegistroAsyncTask(Activity activityActual,List params, String REGISTER_URL) {
           this.activityActual=activityActual;
           this.params=params;
           this.REGISTER_URL= REGISTER_URL;
       }

       @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(activityActual);
            pDialog.setMessage("Espere ..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            try {
                /*
                // Building Parameters
                List params = new ArrayList();


                params.add(new BasicNameValuePair("nombre", modelo_usuario.getNombre()));
                params.add(new BasicNameValuePair("apellido", modelo_usuario.getApellido()));
                params.add(new BasicNameValuePair("nick", modelo_usuario.getNick()));
                params.add(new BasicNameValuePair("password", modelo_usuario.getPassword()));
                params.add(new BasicNameValuePair("foto", modelo_usuario.getFoto()));

                params.add(new BasicNameValuePair("descripcion", modelo_dispositivo.getDescripcion()));
                params.add(new BasicNameValuePair("imei", modelo_dispositivo.getImei()));
                params.add(new BasicNameValuePair("latitud", modelo_dispositivo.getLatitud()));
                params.add(new BasicNameValuePair("longitud", modelo_dispositivo.getLongitud()));
                params.add(new BasicNameValuePair("altura", modelo_dispositivo.getAltura()));


                params.add(new BasicNameValuePair("propietario", "1"));
                */


                //Posting user data to script
                JSONObject json = this.json.makeHttpRequest(REGISTER_URL, "GET", this.params);

                // full json response
                Log.d("Registering attempt", json.toString());

                // json success element
                success = json.getInt("success");
                if (success == 1) {
                    Log.d("Correct Register!", json.toString());

                    //luego de abrir la actividad sigue el asynctask y retorna el mensaje que se mostrara en onPostExecute
                    return json.getString("message");


                } else {
                    Log.d("Fail Register!", json.getString("message"));
                    return json.getString("message");


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String mensage) {
            // dismiss the dialog once product deleted
            // aqui viene lo q se mando cuando e termino el doInBackground
            pDialog.dismiss();
            if (mensage != null) {
                Toast.makeText(activityActual, mensage, Toast.LENGTH_LONG).show();
            }
        }
    }
    }
