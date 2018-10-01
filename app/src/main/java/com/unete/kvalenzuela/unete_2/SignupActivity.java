package com.unete.kvalenzuela.unete_2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
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
    private static final String TAG = "SignupActivity";

    private Retrofit mRestAdapter;
    private UneteApi mUneteApi;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/

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

        // Set up login form.
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
                attemptSignup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
                finish();
            }
        });
    }//END OnCreate

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void showSignupError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void attemptSignup() {
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

        // Check for a valid RAZON_SOCIAL.
        if (TextUtils.isEmpty(razon)) {
            mFloatLabelRazon.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelRazon;
        } else if (razon.length() < 5) {
            mFloatLabelRazon.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelRazon;
        }

        // Check for a valid RESP_NOMBRE.
        if (TextUtils.isEmpty(responsable)) {
            mFloatLabelResponsable.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelResponsable;
        } else if (responsable.length() < 5) {
            mFloatLabelResponsable.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelResponsable;
        }

        // Check for a valid RESP_CEL.
        if (TextUtils.isEmpty(celular)) {
            mFloatLabelCelular.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelCelular;
        } else if (celular.length() < 10) {
            mFloatLabelCelular.setError(getString(R.string.error_invalid_field));
            valid = false;
            focusView = mFloatLabelCelular;
        }

        // Check for a valid RESP_CORREO.
        if (TextUtils.isEmpty(email)) {
            mFloatLabelEmail.setError(getString(R.string.error_field_required));
            valid = false;
            focusView = mFloatLabelEmail;
        } else if (!email.contains("@")) {
            mFloatLabelEmail.setError(getString(R.string.error_invalid_email));
            valid = false;
            focusView = mFloatLabelEmail;
        }

        // Check for a valid PASSWORD.
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

                    //TODO:Show successful Signup message
                    Toast.makeText(getBaseContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();

                    //TODO: Show Main
                    showMainScreen();
                }

                @Override
                public void onFailure(Call<Asociacion> call, Throwable t) {
                    showProgress(false);
                    showSignupError(t.getMessage());
                }
            });
        }
    }//END attempSignup

    private void goLogin() {
        if (!SessionPrefs.get(this).isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    /**Shows the progress UI and hides the login form.*/
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

    private void showMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}