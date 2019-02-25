package com.unete.kvalenzuela.unete_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unete.kvalenzuela.unete_2.api.ApiError;
import com.unete.kvalenzuela.unete_2.api.UneteApi;
import com.unete.kvalenzuela.unete_2.api.model.ApiMessageResponse;
import com.unete.kvalenzuela.unete_2.api.model.Asociacion;
import com.unete.kvalenzuela.unete_2.api.model.ChangeStatusBody;
import com.unete.kvalenzuela.unete_2.api.model.RegistersDisplayList;
import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManagerActivity extends AppCompatActivity {

    private static final String TAG = "ManagerActivity";
    private static final int STATUS_FILTER_DEFAULT_VALUE = 0;

    private Retrofit mRestAdapter;
    private UneteApi mUneteApi;

    // Alert Dialog Manager
   // AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
  //  SessionPrefs session;

    private RecyclerView mRegisterList;
    private ManagerAdapter mManagerAdapter;
    private View mEmptyStateContainer;
    private Spinner mStatusFilterSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ***** Redirección al Login
        if (!SessionPrefs.get(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_manager);

      //  session = new SessionPrefs(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//dfghdgf
        mStatusFilterSpinner = (Spinner) findViewById(R.id.toolbar_spinner);
        ArrayAdapter<String> statusFilterAdapter =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_spinner_item,
                        RegistersDisplayList.STATES_VALUES);
        statusFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStatusFilterSpinner.setAdapter(statusFilterAdapter);
        mStatusFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Ejecutar filtro de citas mÃ©dicas
                String status = parent.getItemAtPosition(position).toString();
                loadAppointments(status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mRegisterList = (RecyclerView) findViewById(R.id.list_appointments);
        mManagerAdapter = new ManagerAdapter(this, new ArrayList<RegistersDisplayList>(0));
        mManagerAdapter.setOnItemClickListener(new ManagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RegistersDisplayList clickedAppointment) {
                // TODO: Codificar acciones de click en items
            }

            @Override
            public void onCancelAppointment(RegistersDisplayList activatedAssociation) {
                // Cancelar cita
                System.out.println("Test ID selected: " + activatedAssociation.getId_AC());
                activateAssociation(activatedAssociation.getId_AC());
            }
        });

        mRegisterList.setAdapter(mManagerAdapter);

        mEmptyStateContainer = findViewById(R.id.empty_state_container);
//rgffh


        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Pedir al servidor informaciÃ³n reciente
                mStatusFilterSpinner.setSelection(STATUS_FILTER_DEFAULT_VALUE);
                loadAppointments(getCurrentState());
            }
        });


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

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAppointments(getCurrentState());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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

    private void loadAppointments(String rawStatus) {
        System.out.println("TEST: status selected: " + rawStatus);
        // Mostrar estado de carga
        showLoadingIndicator(true);

        // Obtener token de usuario
       // String token = SessionPrefs.get(this).getToken();

        String status;

        // Elegir valor del estado segÃºn la opciÃ³n del spinner
        switch (rawStatus) {
            case "Activo":
                status = "ACTIVO";
                break;
            case "Inactivo":
                status = "INACTIVO";
                break;
            case "En Espera":
                status = "EN ESPERA";
                break;
            default:
                status = "TODAS";
        }

        // Construir mapa de parÃ¡metros
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("status", status);
        parameters.put("display", "list");

        // Realizar peticiÃ³n HTTP
        Call<List<RegistersDisplayList>> call = mUneteApi.getRegisters();
        call.enqueue(new Callback<List<RegistersDisplayList>>() {
            @Override
            public void onResponse(Call<List<RegistersDisplayList>> call,
                                   Response<List<RegistersDisplayList>> response) {
                if (!response.isSuccessful()) {
                    // Procesar error de API
                    String error = "Ha ocurrido un error. Contacte al administrador";
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("application/json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                        error = apiError.getMessage();
                        Log.d(TAG, apiError.getDeveloperMessage());
                    } else {
                        try {
                            // Reportar causas de error no relacionado con la API
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    showLoadingIndicator(false);
                    showErrorMessage(error);
                    return;
                }

                List<RegistersDisplayList> serverAppointments = response.body();

                if (serverAppointments.size() > 0) {
                    // Mostrar lista de citas mÃ©dicas
                    showLoadingIndicator(false);
                    showAppointments(serverAppointments);
                } else {
                    // Mostrar empty state
                    showLoadingIndicator(false);
                    showNoAppointments();
                }

            }

            @Override
            public void onFailure(Call<List<RegistersDisplayList>> call, Throwable t) {
                showLoadingIndicator(false);
                Log.d(TAG, "PeticiÃ³n rechazada:" + t.getMessage());
                showErrorMessage("Error de comunicaciÃ³n");
            }
        });
    }

    private void activateAssociation(int id) {
        showLoadingIndicator(true);

        // Obtener token de usuario
        String token = SessionPrefs.get(this).getToken();

        // Preparar cuerpo de la peticiÃ³n
        HashMap<String, String> statusMap = new HashMap<>();
        statusMap.put("Estatus", "ACTIVO");

        String status = "ACTIVO";

        // Enviar peticiÃ³n
        Call<Asociacion> changeCall = mUneteApi.changeStatus(new ChangeStatusBody(id, status));
        changeCall.enqueue(
                new Callback<Asociacion>() {
                    @Override
                    public void onResponse(Call<Asociacion> call,
                                           Response<Asociacion> response) {
                        if (!response.isSuccessful()) {
                            // Procesar error de API}
                            System.out.println("TEST ERROR: "+response.errorBody());
                            System.out.println("TEST ERROR: "+response.message());
                            System.out.println("TEST ERROR: "+response.raw().isSuccessful());

                            String error = "Ha ocurrido un error. Contacte al administrador";
                            if (response.errorBody()
                                    .contentType()
                                    .subtype()
                                    .equals("application/json")) {
                                ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                                error = apiError.getMessage();
                                Log.d(TAG, apiError.getDeveloperMessage());
                            } else {
                                try {
                                    // Reportar causas de error no relacionado con la API
                                    Log.d(TAG, response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            showLoadingIndicator(false);
                            showErrorMessage(error);
                            return;
                        }

                        // CancelaciÃ³n Exitosa
                        showLoadingIndicator(false);
                        Log.d(TAG, response.body().getRazon_Social() + " Activada");
                        Toast.makeText(getBaseContext(), response.body().getRazon_Social() + " Activada", Toast.LENGTH_SHORT).show();
                        loadAppointments(getCurrentState());

                    }

                    @Override
                    public void onFailure(Call<Asociacion> call, Throwable t) {
                        showLoadingIndicator(false);
                        Log.d(TAG, "PeticiÃ³n rechazada:" + t.getMessage());
                        showErrorMessage("Error de comunicaciÃ³n");
                    }
                }
        );
    }

    private String getCurrentState() {
        String status = (String) mStatusFilterSpinner.getSelectedItem();
        return status;
    }

    private void showLoadingIndicator(final boolean show) {
        final SwipeRefreshLayout refreshLayout =
                (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(show);
            }
        });
    }

    private void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void showNoAppointments() {
        mRegisterList.setVisibility(View.GONE);
        mEmptyStateContainer.setVisibility(View.VISIBLE);
    }

    private void showAppointments(List<RegistersDisplayList> serverAppointments) {
        mManagerAdapter.swapItems(serverAppointments);

        mRegisterList.setVisibility(View.VISIBLE);
        mEmptyStateContainer.setVisibility(View.GONE);
    }




}