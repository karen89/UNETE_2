package com.unete.kvalenzuela.unete_2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.unete.kvalenzuela.unete_2.api.Asociacion;
import com.unete.kvalenzuela.unete_2.api.SignupBody;
import com.unete.kvalenzuela.unete_2.api.UneteApi;
import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private Retrofit mRestAdapter;
    private UneteApi mUneteApi;

    private static final String TAG = "SignupActivity";

    //UI references
    private View mProgressView;
    private View mSignupForm;
    private TextInputLayout mFloatLabelRazon;
    private EditText mNameAC;
    private TextInputLayout mFloatLabelResponsable;
    private EditText mResponsable;
    private TextInputLayout mFloatLabelCelular;
    private EditText mCelular;
    private TextInputLayout mFloatLabelEmail;
    private EditText mEmail;
    private TextInputLayout mFloatLabelPassword;
    private EditText mPassword;
    private Button mSignupBtn;
    private TextView _loginLink;

    /*@InjectView(R.id.input_name_ac)
    EditText _nameText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
   // @InjectView(R.id.link_login)
    TextView _loginLink;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //ButterKnife.inject(this);

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

        // Set up the login form.
        mProgressView = findViewById(R.id.signup_progress);
        mSignupForm = findViewById(R.id.signup_form);
        mFloatLabelRazon = findViewById(R.id.float_label_razon);
        mNameAC = findViewById(R.id.input_name_ac);
        mFloatLabelResponsable = findViewById(R.id.float_label_responsable);
        mResponsable = findViewById(R.id.input_responsable);
        mFloatLabelCelular = findViewById(R.id.float_label_celular);
        mCelular = findViewById(R.id.input_celular);
        mFloatLabelEmail = findViewById(R.id.float_label_email);
        mEmail = findViewById(R.id.input_email);
        mFloatLabelPassword = findViewById(R.id.float_label_password);
        mPassword = findViewById(R.id.input_password);
        mSignupBtn = findViewById(R.id.btn_signup);
        _loginLink = findViewById(R.id.link_login);

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnline()) {
                    showSignupError(getString(R.string.error_network));
                    return;
                }
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
               /* if (SessionPrefs.get(this).isLoggedIn()) { //camiar por una funcion
                    //no hace nada
                } else {*/
                    loguearse();
                    finish();
               // }
            }
        });
    }

    private void loguearse() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();    }

    private void showSignupError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void signup() {
        Log.d(TAG, "SIGNUP");

        // Reset errors.
        mFloatLabelRazon.setError(null);
        mFloatLabelResponsable.setError(null);
        mFloatLabelCelular.setError(null);
        mFloatLabelEmail.setError(null);
        mFloatLabelPassword.setError(null);

        String razon = mNameAC.getText().toString();
        String responsable = mResponsable.getText().toString();
        String celular = mCelular.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        boolean valid = true;
        View focusView = null;

        // Check for a valid razon address.
        if (TextUtils.isEmpty(razon)) {
            mFloatLabelRazon.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelRazon;
        } else if (razon.length() < 5) {
            mFloatLabelRazon.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelRazon;
        }

        // Check for a valid responsable address.
        if (TextUtils.isEmpty(responsable)) {
            mFloatLabelResponsable.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelResponsable;
        } else if (responsable.length() < 5) {
            mFloatLabelResponsable.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelResponsable;
        }

        // Check for a valid Celular address.
        if (TextUtils.isEmpty(celular)) {
            mFloatLabelCelular.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelCelular;
        } else if (celular.length() < 10) {
            mFloatLabelCelular.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelCelular;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mFloatLabelEmail.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelEmail;
        } else if (!email.contains("@")) {
            mFloatLabelEmail.setError(getString(R.string.error_invalid_email));
            valid = false;
            focusView = mFloatLabelEmail;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelPassword;
        } else if (password.length() < 4) {
            mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
            valid = false;
            focusView = mFloatLabelPassword;
        }

        if (!valid) {
           //Wait for validate true
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Call<Asociacion> signupCall = mUneteApi.signup(new SignupBody(razon, responsable, celular, email, password));
            signupCall.enqueue(new Callback<Asociacion>() {
                @Override
                public void onResponse(Call<Asociacion> call, Response<Asociacion> response) {
                    // Mostrar progreso
                    showProgress(false);

                    // Procesar errores
                    if (!response.isSuccessful()) {
                        String error = "Ha ocurrido un error. Contacte al administrador";
                        if (response.errorBody()
                                .contentType()
                                .subtype()
                                .equals("application/json")) {
                            ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                            error = apiError.getMessage();
                            Log.d("SignupActivity", apiError.getDeveloperMessage());
                        } else {
                            //error = response.message();

                            try {
                                // Reportar causas de error no relacionado con la API
                                Log.d("SignupActivity", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        showSignupError(error);
                        return;
                    }

                    // Guardar afiliado en preferencias --se aplico en login
                    //SessionPrefs.get(LoginActivity.this).saveAffiliate(response.body());

                    //Setea los campos a vacios
                    reiniciarSignup();
                    //mostrar notificacion de registrado

                }

                @Override
                public void onFailure(Call<Asociacion> call, Throwable t) {
                    showProgress(false);
                    showSignupError(t.getMessage());
                }
            });
        }

        /*mSignupBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.ThemeOverlay_AppCompat_Dark);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creando cuenta...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }

    private void reiniciarSignup() {
        mNameAC.setText("");
        mResponsable.setText("");
        mCelular.setText("");
        mEmail.setText("");
        mPassword.setText("");
    }


    public void onSignupSuccess() {
        mSignupBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        mSignupBtn.setEnabled(false);
    }

    /*public boolean validate() {
        // Reset errors.
        mFloatLabelRazon.setError(null);
        mFloatLabelResponsable.setError(null);
        mFloatLabelCelular.setError(null);
        mFloatLabelEmail.setError(null);
        mFloatLabelPassword.setError(null);

        String razon = mNameAC.getText().toString();
        String responsable = mResponsable.getText().toString();
        String celular = mCelular.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        boolean valid = true;
        View focusView = null;

        // Check for a valid razon address.
        if (TextUtils.isEmpty(razon)) {
            mFloatLabelRazon.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelRazon;
        } else if (razon.length() < 5) {
            mFloatLabelRazon.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelRazon;
        }

        // Check for a valid responsable address.
        if (TextUtils.isEmpty(responsable)) {
            mFloatLabelResponsable.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelResponsable;
        } else if (responsable.length() < 5) {
            mFloatLabelResponsable.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelResponsable;
        }

        // Check for a valid Celular address.
        if (TextUtils.isEmpty(celular)) {
            mFloatLabelCelular.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelCelular;
        } else if (celular.length() < 10) {
            mFloatLabelCelular.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelCelular;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mFloatLabelEmail.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelEmail;
        } else if (email.contains("@")) {
            mFloatLabelEmail.setError(getString(R.string.error_invalid_email));
            valid = false;
            focusView = mFloatLabelEmail;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelPassword;
        } else if (password.length() < 4) {
            mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
            valid = false;
            focusView = mFloatLabelPassword;
        }

        *//*if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Más de 3 caracteres");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Ingresa correo válido");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
*//*
        if (!valid) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        return valid;
    }*/

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignupForm.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignupForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignupForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignupForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}