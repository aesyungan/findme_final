package com.example.xl.findnow_final;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accesodatos.DispositivoAD;
import com.example.accesodatos.UsuarioAD;
import com.example.entidades.ClassStaticApp.EstadoTelefono;
import com.example.entidades.ClassStaticApp.UsuarioSingleton;
import com.example.logicanegocios.DispositivoLN;
import com.example.logicanegocios.UsuarioLN;
import com.example.xl.findnow_final.ResursosApp.Service_GPS_APP;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Fragment_Mapa.OnFragmentInteractionListener,
        ScannerQr.OnFragmentInteractionListener,
        Acerca.OnFragmentInteractionListener

{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //header datos
        View header = navigationView.getHeaderView(0);
        TextView usuario_name_logeado = (TextView) header.findViewById(R.id.tvNombre);
        usuario_name_logeado.setText(UsuarioSingleton.getInstance().asignacion.getUsuario().getNombres()+" "+UsuarioSingleton.getInstance().asignacion.getUsuario().getApellidos());
        TextView usuario_cedula_logeado = (TextView) header.findViewById(R.id.tvDescripcion);
        usuario_cedula_logeado.setText(UsuarioSingleton.getInstance().asignacion.getUsuario().getCedula());
        ImageView user_foto = (ImageView) header.findViewById(R.id.imgVFotoUser);
        UsuarioLN usuarioLN= new UsuarioLN();
        //descarga la imagen
        usuarioLN.DescargarImagen(user_foto,UsuarioSingleton.getInstance().asignacion.getUsuario().getFoto());
        //animacion a la imagen
        ImageView imageViewNavHeaderTelefono = (ImageView) header.findViewById(R.id.imageViewNavHeaderTelefono);
        Animation ani = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imageViewNavHeaderTelefono.startAnimation(ani);

        //fragmento por defecto
        Fragment_Mapa fragment_mapa= new Fragment_Mapa();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContenedorTodo,fragment_mapa).commit();
        //cargar datos del delefono
        // saca el contexto del menu principal
        EstadoTelefono.context=getApplicationContext();
        EstadoTelefono.view=getWindow().getDecorView().getRootView();
        EstadoTelefono.resources=getResources();
        EstadoTelefono.activity=MenuPrincipal.this;
        //guardardatosLocal
        //local Data Base

        

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            UsuarioLN usuarioLN = new UsuarioLN();
            DispositivoLN dispositivoLN = new DispositivoLN();
            stopService(new Intent(getApplicationContext(), Service_GPS_APP.class));
            usuarioLN.LocalBorrarAll(getApplicationContext());
            dispositivoLN.LocalBorrarAll(getApplicationContext());
            Toast.makeText(getApplicationContext(),"Logout ..",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //cambiar el fragmento
            Fragment_Mapa fragment_mapa= new Fragment_Mapa();
            CargarFragmet(fragment_mapa);
           // FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
           // transaction.replace(R.id.fragmentContenedorTodo,fragment_mapa);
            //transaction.commit();

        } else if (id == R.id.nav_gallery) {
            //cambiar el fragmento
            ScannerQr fragment_mapa= new ScannerQr();
            CargarFragmet(fragment_mapa);
           // FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragmentContenedorTodo,fragment_mapa);
            //transaction.commit();

        } else if (id == R.id.nav_slideshow) {

            Acerca fragment = new Acerca();
            CargarFragmet(fragment);
            //  FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            // transaction.replace(R.id.fragmentContenedorTodo,fragment);
            //transaction.commit();
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(),"Share Facebook ..",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_send) {
            UsuarioLN usuarioLN = new UsuarioLN();
            DispositivoLN dispositivoLN = new DispositivoLN();
            stopService(new Intent(getApplicationContext(), Service_GPS_APP.class));
            usuarioLN.LocalBorrarAll(getApplicationContext());
            dispositivoLN.LocalBorrarAll(getApplicationContext());
            Toast.makeText(getApplicationContext(),"Logout ..",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void CargarFragmet(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContenedorTodo,fragment);
        transaction.commit();
    }
}
