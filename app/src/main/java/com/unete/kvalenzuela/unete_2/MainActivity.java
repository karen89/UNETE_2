package com.unete.kvalenzuela.unete_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;
import com.unete.kvalenzuela.unete_2.api.ui.CategoryListActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;

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

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotsPanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for(int i = 0; i < dotsCount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Timer para el slider
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerSliderTask(), 2000, 4000);


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
            Intent intent = new Intent(this, CategoryListActivity.class);
            intent.putExtra("Categoria_Id_Cat", "0");
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
            if (SessionPrefs.get(this).isLoggedIn()) {
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

    public void donateScreen(View view) {
        Intent intent = new Intent(this, DonateUNEActivity.class);
        startActivity(intent);
    }

    public void animalsCat(View view) { //animales ID=1
        Intent intent = new Intent(this, CategoryListActivity.class);
        String id = "1";
        intent.putExtra("Categoria_Id_Cat", id);
        //intent.putExtra("Categoria_Id_Cat", "1");
        startActivity(intent);
    }

    public void alimentationCat(View view) {    //alimentacion ID=2
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "2");
        startActivity(intent);
    }

    public void educationCat(View view) {   //educacion ID=3
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "3");
        startActivity(intent);
    }

    public void medioAmbienteCat(View view) {   //medio ambiente ID=4
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "4");
        startActivity(intent);
    }

    public void healthCat(View view) {  //salud ID=5
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "5");
        startActivity(intent);
    }

    public void sportsCat(View view) {  //deportivas ID=6
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "6");
        startActivity(intent);
    }

    public void asistenciaCat(View view) {  //asistencia social ID=7
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "7");
        startActivity(intent);
    }

    public void otherCat(View view) {   //otros ID=8
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra("Categoria_Id_Cat", "8");
        startActivity(intent);
    }

    //Clase interna que funciona como timer del slider
    public class TimerSliderTask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else if (viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }else if (viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(4);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
