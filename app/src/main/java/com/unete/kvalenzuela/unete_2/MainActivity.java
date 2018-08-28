package com.unete.kvalenzuela.unete_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.SnackbarContentLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;
import com.unete.kvalenzuela.unete_2.itemListDetail.ItemListActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //app_bar_main
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout); //act_main
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);//MENU
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() { //drawer_layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //menu settings
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //accion settings
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            if (SessionPrefs.get(this).isLoggedIn()) {
                System.out.println("Cerrando Sesión");
                //TODO: mostrar notificacion de cerrado
                SessionPrefs.get(this).logOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else { //termina la aplicación
                finish();
                System.exit(0);
            }
            return true;
        } else if (id == R.id.action_about) {
            //TODO:
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) { //acciones menu
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_ac) {
            Intent intent = new Intent(this, ItemListActivity.class);
            startActivity(intent);
//        } else if (id == R.id.nav_map) {
//
//        } else if (id == R.id.nav_event) {
//
//        } else if (id == R.id.nav_une) {
//
//        } else if (id == R.id.nav_donate) {

        } else if (id == R.id.nav_perfil) {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_login) {
            // Redirección al Login
            if (SessionPrefs.get(this).isLoggedIn()) {
                //Redirige a perfil
                //TODO: mensaje: "Estas logueado!"
                Intent intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_signup) {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
//        } else if (id == R.id.nav_comment) {

        } else if (id == R.id.nav_about) {
            //TODO:
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //TODO: ACCIONES PARA CARGAR LAS AC SEGUN CATEGORÍAS
    //TODO: QUE SEA UNA FUNCION POR CATEGORIA O CHECAR SWITCH
    /*Called when the user click "Donar $2" button*/
    public void donateScreen(View view) {
        Intent intent = new Intent(this, DonateUNEActivity.class);
        startActivity(intent);
    }

    /*Called when the user click "Donar $2" button*/
    public void medioAmbienteCat(View view) {
        //Carga la lista de AC de medio ambiente
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }

}
