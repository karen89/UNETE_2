package com.unete.kvalenzuela.unete_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unete.kvalenzuela.unete_2.api.ApiError;
import com.unete.kvalenzuela.unete_2.api.model.Asociacion;
import com.unete.kvalenzuela.unete_2.api.model.ProfileBody;
import com.unete.kvalenzuela.unete_2.api.UneteApi;
import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    TextInputLayout floatLabelMunicipio;
    EditText municipio;
    TextInputLayout floatLabelEstado;
    EditText estado;

    TextView contacto;
    TextInputLayout floatLabelResponsable;
    EditText responsable;
    TextInputLayout floatLabelCelular;
    EditText celular;
    TextInputLayout floatLabelCorreo;
    EditText correo;

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

        session = new SessionPrefs(getApplicationContext());

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

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // Crear conexión al servicio REST
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(UneteApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Crear conexión a la API
        mUneteApi = mRestAdapter.create(UneteApi.class);

        //Setup profile form
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
        floatLabelMunicipio = (TextInputLayout) findViewById(R.id.float_label_localidad);
        municipio = (EditText) findViewById(R.id.input_localidad);
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

        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        /** Get user data from session* */
        HashMap<String, String> user = session.getUserDetails();

        //Set data from PREFs to Variables
        String razon_ = user.get(SessionPrefs.PREF_AC_RAZONSOCIAL);
        String descripcion_ = user.get(SessionPrefs.PREF_AC_DESCRIPCION);
        String calle_ = user.get(SessionPrefs.PREF_CALLE);
        String numero_ = user.get(SessionPrefs.PREF_NUMERO);
        String cruzam_1_ = user.get(SessionPrefs.PREF_CRUZAM_1);
        String cruzam_2_ = user.get(SessionPrefs.PREF_CRUZAM_2);
        String colonia_ = user.get(SessionPrefs.PREF_COLONIA);
        String cp_ = user.get(SessionPrefs.PREF_CP);
        String municipio_ = user.get(SessionPrefs.PREF_MUNICIPIO);
        String estado_ = user.get(SessionPrefs.PREF_ESTADO);
        String representante_ = user.get(SessionPrefs.PREF_AC_REPNOMBRE);
        String celular_ = user.get(SessionPrefs.PREF_AC_REPCEL);
        String correo_ = user.get(SessionPrefs.PREF_AC_REPCORREO);
        String cuentaBancaria_ = user.get(SessionPrefs.PREF_AC_CUENTABANCARIA);
        final String token_ = user.get(SessionPrefs.PREF_AC_TOKEN);
        //String subcategoria_ = user.get(SessionPrefs.PREF_AC_SUBCATEGORIA_ID);

        //Set data from Variables to UI
        razonSocial.setText(razon_);
        descripcion.setText(descripcion_);
        calle.setText(calle_);
        numero.setText(numero_);
        cruzam1.setText(cruzam_1_);
        cruzam2.setText(cruzam_2_);
        colonia.setText(colonia_);
        cp.setText(cp_);
        municipio.setText(municipio_);
        estado.setText(estado_);
        responsable.setText(representante_);
        celular.setText(celular_);
        correo.setText(correo_);
        cuentaBancaria.setText(cuentaBancaria_);

        /**Logout button click event* */
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "UPDATE_PROFILE");

                /** --Validate fields-- **/
                //Get data from UI
                String _razon = razonSocial.getText().toString();
                String _descripcion = descripcion.getText().toString();
                String _calle = calle.getText().toString();
                String _numero = numero.getText().toString();
                String _cruzam_1 = cruzam1.getText().toString();
                String _cruzam_2 = cruzam2.getText().toString();
                String _colonia = colonia.getText().toString();
                String _cp = cp.getText().toString();
                String _municipio = municipio.getText().toString();
                String _estado = estado.getText().toString();
                String _responsable = responsable.getText().toString();
                String _celular = celular.getText().toString();
                String _correo = correo.getText().toString();
                String _cuentaBancaria = cuentaBancaria.getText().toString();

                boolean valid = true;
                View focusView = null;

                // Check for a valid RAZON_SOCIAL.
                if (TextUtils.isEmpty(_razon)) {
                    floatLabelRazonSocial.setError(getString(R.string.error_field_required));
                    valid = false;
                    focusView = floatLabelRazonSocial;
                } else if (_razon.length() < 5) {
                    floatLabelRazonSocial.setError(getString(R.string.error_invalid_field));
                    valid = false;
                    focusView = floatLabelRazonSocial;
                }

                // Check for a valid RESP_NOMBRE.
                if (TextUtils.isEmpty(_responsable)) {
                    floatLabelResponsable.setError(getString(R.string.error_field_required));
                    valid = false;
                    focusView = floatLabelResponsable;
                } else if (_responsable.length() < 5) {
                    floatLabelResponsable.setError(getString(R.string.error_invalid_field));
                    valid = false;
                    focusView = floatLabelResponsable;
                }

                // Check for a valid RESP_CEL.
                if (TextUtils.isEmpty(_celular)) {
                    floatLabelCelular.setError(getString(R.string.error_field_required));
                    valid = false;
                    focusView = floatLabelCelular;
                } else if (_celular.length() < 10) {
                    floatLabelCelular.setError(getString(R.string.error_invalid_field));
                    valid = false;
                    focusView = floatLabelCelular;
                }

                // Check for a valid RESP_CORREO.
                if (TextUtils.isEmpty(_correo)) {
                    floatLabelCorreo.setError(getString(R.string.error_field_required));
                    valid = false;
                    focusView = floatLabelCorreo;
                } else if (!_correo.contains("@")) {
                    floatLabelCorreo.setError(getString(R.string.error_invalid_email));
                    valid = false;
                    focusView = floatLabelCorreo;
                }
                /** --Save data at DB-- **/
                if (!valid) {
                    //Wait for validate true
                    focusView.requestFocus();
                } else {
                    // Background task to perform the update.
                    Call<Asociacion> updateCall = mUneteApi.update(new ProfileBody(
                            _razon, _descripcion, _calle, _numero, _cruzam_1, _cruzam_2, _colonia,
                            _cp, _municipio, _estado, _responsable, _celular, _correo, _cuentaBancaria, token_));
                    updateCall.enqueue(new Callback<Asociacion>() {
                        @Override
                        public void onResponse(Call<Asociacion> call, Response<Asociacion> response) {
                            // Procesar errores
                            if (!response.isSuccessful()) {
                                String error = "Ha ocurrido un error. Contacte al administrador";
                                if (response.errorBody()
                                        .contentType()
                                        .subtype()
                                        .equals("application/json")) {
                                    ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                                    error = apiError.getMessage();
                                    Log.d("ProfileActivity", apiError.getDeveloperMessage());
                                } else {
                                    //error = response.message();
                                    try {
                                        // Reportar causas de error no relacionado con la API
                                        Log.d("ProfileActivity", response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                showProfileError(error);
                                return;
                            }

                            //Clear the session data preview
                            SessionPrefs.get(PerfilActivity.this).logOut();
                            //Save new data at SessionPref
                            SessionPrefs.get(PerfilActivity.this).createSession(response.body());
                            //Show successful update message
                            Toast.makeText(getBaseContext(), "Actualizacion Exitosa", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Asociacion> call, Throwable t) {
                            showProfileError(t.getMessage());
                        }
                    });
                }
            }
        });
    }//END OnCreate

    private void showProfileError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}
