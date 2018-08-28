package com.unete.kvalenzuela.unete_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unete.kvalenzuela.unete_2.api.UneteApi;
import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;

import java.util.HashMap;

import retrofit2.Retrofit;

public class PerfilActivity extends AppCompatActivity {
    private static final String TAG = "PerfilActivity";

    private Retrofit mRestAdapter;
    private UneteApi mUneteApi;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionPrefs session;

    TextView detalleAsociacion;
    TextInputLayout floatLabelRazonSocial;
    EditText razonSocial;
    TextInputLayout floatLabelDescripcion;
    EditText descripcion;

    TextView localizacion;
    TextInputLayout floatLabelCalle;
    EditText calle;
    TextInputLayout floatLabelNumero;
    EditText numero;
    TextInputLayout floatLabelCruz1;
    EditText cruzam1;
    TextInputLayout floatLabelCruz2;
    EditText cruzam2;
    TextInputLayout floatLabelColonia;
    EditText colonia;
    TextInputLayout floatLabelCP;
    EditText cp;
    TextInputLayout floatLabelLocalidad;
    EditText localidad;
    TextInputLayout floatLabelEstado;
    EditText estado;

    TextView contacto;
    TextInputLayout floatLabelResponsable;
    EditText responsable;
    TextInputLayout floatLabelCorreo;
    EditText correo;
    TextInputLayout floatLabelCelular;
    EditText celular;

    TextView cuenta;
    TextInputLayout floatLabelCuentaBancaria;
    EditText cuentaBancaria;

    Button btnUpdateProfile;
    View formProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Redirección al Login
        if (!SessionPrefs.get(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_perfil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //--REDIRIGIR A AGREGAR CALENDARIO----
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        formProfile = (View) findViewById(R.id.form_profile);
        detalleAsociacion = (TextView) findViewById(R.id.detalle_asociacion);
        floatLabelRazonSocial = (TextInputLayout) findViewById(R.id.float_label_razon);
        razonSocial = (EditText) findViewById(R.id.input_razonSocial);
        floatLabelDescripcion = (TextInputLayout) findViewById(R.id.float_label_descripcion);
        descripcion = (EditText) findViewById(R.id.input_descrip);

        localizacion = (TextView) findViewById(R.id.localizacion);
        floatLabelCalle = (TextInputLayout) findViewById(R.id.float_label_calle);
        calle = (EditText) findViewById(R.id.input_calle);
        floatLabelNumero = (TextInputLayout) findViewById(R.id.float_label_numero);
        numero = (EditText) findViewById(R.id.input_numero);
        floatLabelCruz1 = (TextInputLayout) findViewById(R.id.float_label_cruz1);
        cruzam1 = (EditText) findViewById(R.id.input_cruz1);
        floatLabelCruz2 = (TextInputLayout) findViewById(R.id.float_label_cruz2);
        cruzam2 = (EditText) findViewById(R.id.input_cruz2);
        floatLabelColonia = (TextInputLayout) findViewById(R.id.float_label_colonia);
        colonia = (EditText) findViewById(R.id.input_colonia);
        floatLabelCP = (TextInputLayout) findViewById(R.id.float_label_cp);
        cp = (EditText) findViewById(R.id.input_cp);
        floatLabelLocalidad = (TextInputLayout) findViewById(R.id.float_label_localidad);
        localidad = (EditText) findViewById(R.id.input_localidad);
        floatLabelEstado = (TextInputLayout) findViewById(R.id.float_label_estado);
        estado = (EditText) findViewById(R.id.input_estado);

        contacto = (TextView) findViewById(R.id.contacto);
        floatLabelResponsable = (TextInputLayout) findViewById(R.id.float_label_responsable);
        responsable = (EditText) findViewById(R.id.input_responsable);
        floatLabelCorreo = (TextInputLayout) findViewById(R.id.float_label_correo_perfil);
        correo = (EditText) findViewById(R.id.input_correo_perfil);
        floatLabelCelular = (TextInputLayout) findViewById(R.id.float_label_celular_perfil);
        celular = (EditText) findViewById(R.id.input_celular_perfil);

        cuenta = (TextView) findViewById(R.id.cuenta);
        floatLabelCuentaBancaria = (TextInputLayout) findViewById(R.id.float_label_cuenta_perfil);
        cuentaBancaria = (EditText) findViewById(R.id.input_cuenta_perfil);

        btnUpdateProfile = (Button) findViewById(R.id.btn_update_profile);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        //get data at variable
        String razon = user.get(SessionPrefs.PREF_AC_RAZONSOCIAL);
        String representante = user.get(SessionPrefs.PREF_AC_REPNOMBRE);
        String celular = user.get(SessionPrefs.PREF_AC_REPCEL);
        String correo = user.get(SessionPrefs.PREF_AC_REPCORREO);
        String descripcion = user.get(SessionPrefs.PREF_AC_DESCRIPCION);
        String cuentaBancaria = user.get(SessionPrefs.PREF_AC_CUENTABANCARIA);
        String subcategoria = user.get(SessionPrefs.PREF_AC_SUBCATEGORIA_ID);

        //set data at ui

        razonSocial.setText(razon);
        responsable.setText(representante);



        /**
         * Logout button click event
         * */
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO:Validate fields
                // TODO:Clear the session data preview

                // TODO:Save new data at SessionPref

                // TODO:Save new data at db
                updateProfile();
            }
        });

    }


    private void updateProfile() {
        //TODO: send data to server
    }
}
