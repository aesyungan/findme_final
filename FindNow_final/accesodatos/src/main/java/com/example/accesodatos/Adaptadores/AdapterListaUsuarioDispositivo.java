package com.example.accesodatos.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.accesodatos.R;
import com.example.accesodatos.UsuarioAD;
import com.example.entidades.Asignacion;


import java.util.List;

/**
 * Created by Aes on 14/10/2016.
 */

public class AdapterListaUsuarioDispositivo extends ArrayAdapter<Asignacion> {

    public AdapterListaUsuarioDispositivo(Context context, List<Asignacion> dato) {
        super(context, 0, dato);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Asignacion item=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.row_dispositivo,parent,false);
        }
        ImageView img=(ImageView)convertView.findViewById(R.id.row_img);
        TextView nombre=(TextView)convertView.findViewById(R.id.row_txt_nombre);
        TextView imei=(TextView)convertView.findViewById(R.id.row_txt_imei);
        TextView descripcion=(TextView)convertView.findViewById(R.id.row_txt_Descripcion);
        nombre.setText(item.getUsuario().getNombres()+" "+item.getUsuario().getApellidos());
        descripcion.setText(item.getDispositivo().getDescripcion());
        imei.setText(item.getDispositivo().getImei());
      //  cargarImagen(img,item.getFoto());//carga la imagen en un hilo
        UsuarioAD usuarioLN = new UsuarioAD();
        usuarioLN.DescargarImagen(img,item.getUsuario().getFoto());
        //new DownloadImageTask(img).execute(item.getFoto());

        return convertView;
    }
//para cargar la foto de manera asincronica



}
