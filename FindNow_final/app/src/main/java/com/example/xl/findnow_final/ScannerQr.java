package com.example.xl.findnow_final;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.entidades.Asignacion;
import com.example.entidades.ClassStaticApp.EstadoTelefono;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.entidades.Dispositivo;
import com.example.entidades.Usuario;
import com.example.logicanegocios.AsignacionLN;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerQr extends Fragment {

    private FloatingActionButton scan_btn;
    String id_dispositivo_Scanneado="";
    ImageView imageViewQr;
    public ScannerQr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_scanner_qr, container, false);
        imageViewQr = (ImageView) view.findViewById(R.id.fra_imageViewQr);
        scan_btn = (FloatingActionButton) view.findViewById(R.id.fra_buttonScannerQr);
        final Activity activity = getActivity();

        Resources res = getResources();

        TabHost tabs = (TabHost) view.findViewById(R.id.tabHostQr);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("Codigo");
        spec.setContent(R.id.TabCodigoQr);
        spec.setIndicator("",
                res.getDrawable(R.drawable.qrcode));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.TabScanner);
        spec.setIndicator("",
                res.getDrawable(R.drawable.scanner));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);//por default
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                // Toast.makeText(getContext(), "id" + s, Toast.LENGTH_LONG).show();

                Log.d("id",""+s);
            }
        });

//generarQr


        //para scannear

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // IntentIntegrator integrator = new IntentIntegrator(activity);
                IntentIntegrator integrator = new IntentIntegrator(getActivity()) {
                    @Override
                    protected void startActivityForResult(Intent intent, int code) {
                        ScannerQr.this.startActivityForResult(intent, IntentIntegrator.REQUEST_CODE); // REQUEST_CODE override

                    }
                };

                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan..");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });
        Toast.makeText(EstadoTelefono.context,"IdUser:"+Integer.toString(UsuarioSingleton.getInstance().asignacion.getDispositivo().getId_dispositivo()),Toast.LENGTH_LONG).show();

        generarQr(Integer.toString(UsuarioSingleton.getInstance().asignacion.getDispositivo().getId_dispositivo()));
        return view;
    }

    //generar  QR

    public void generarQr(  String text2Qr){


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,150,150);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageViewQr.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    //
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //   id_dispositivo_Scanneado=result.getContents().toString();

            if (result.getContents() == null) {
                Toast.makeText(getContext(), "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(EstadoTelefono.context, result.getContents(),Toast.LENGTH_LONG).show();
                id_dispositivo_Scanneado = result.getContents().toString();
                Asignacion asignacion= new Asignacion();
                Usuario usuario= new Usuario();
                Dispositivo dispositivo= new Dispositivo();

                usuario=UsuarioSingleton.getInstance().asignacion.getUsuario();//optiene el usuario
                dispositivo.setId_dispositivo(Integer.parseInt(result.getContents().toString()));

                asignacion.setDispositivo(dispositivo);
                asignacion.setUsuario(usuario);

                AsignacionLN asignacionLN = new AsignacionLN();
                asignacionLN.Insertar(EstadoTelefono.activity,asignacion);
                Log.d("Datos_scanner",id_dispositivo_Scanneado);

            }
        } else {
            Toast.makeText(getContext(),"hola Result",Toast.LENGTH_LONG).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
