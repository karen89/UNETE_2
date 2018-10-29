package com.unete.kvalenzuela.unete_2.api.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.unete.kvalenzuela.unete_2.R;
import com.unete.kvalenzuela.unete_2.api.ApiError;
import com.unete.kvalenzuela.unete_2.api.UneteApi;
import com.unete.kvalenzuela.unete_2.api.model.AllListBody;
import com.unete.kvalenzuela.unete_2.api.model.ApiResponseCategories;
import com.unete.kvalenzuela.unete_2.api.model.Asociacion;
import com.unete.kvalenzuela.unete_2.api.model.CategoryDisplayList;
import com.unete.kvalenzuela.unete_2.api.model.CategoryListBody;
import com.unete.kvalenzuela.unete_2.api.model.LoginBody;
import com.unete.kvalenzuela.unete_2.api.prefs.SessionPrefs;
import com.unete.kvalenzuela.unete_2.api.ui.CategoriesAdapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
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

public class CategoryListActivity extends AppCompatActivity  {
    private static final String TAG = "CATEGORY LIST ACTIVITY";

    String categoryFromIntent;

    private Retrofit mRestAdapter;
    private UneteApi mUneteApi;

    private RecyclerView mCategoriesList;
    private CategoriesAdapter mCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        categoryFromIntent = intent.getStringExtra("Categoria_Id_Cat");
        System.out.println("Categoria: " + categoryFromIntent);//

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

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

        mRestAdapter = new Retrofit.Builder()
                .baseUrl(UneteApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mUneteApi = mRestAdapter.create(UneteApi.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        switch (categoryFromIntent){
            case "0":
                setTitle("Todas las fundaciones registradas");
                break;
            case "1":
                setTitle("Fundaciones de Animales");
                break;
            case "2":
                setTitle("Fundaciones de Alimentacion");
                break;
            case "3":
                setTitle("Fundaciones de Educacion");
                break;
            case "4":
                setTitle("Fundaciones de Medio Ambiente");
                break;
            case "5":
                setTitle("Fundaciones de Salud");
                break;
            case "6":
                setTitle("Fundaciones Deportivas");
                break;
            case "7":
                setTitle("Fundaciones de Asistencia Social");
                break;
            case "8":
                setTitle("Otras Fundaciones");
                break;
            default:
                System.out.println("Categoría inválida");
                break;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //TODO: validar si categoryFromIntent es null

        loadAppointments(categoryFromIntent);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mCategoriesList = (RecyclerView) findViewById(R.id.list_categories);
        mCategoriesAdapter = new CategoriesAdapter(this, new ArrayList<CategoryDisplayList>(0));
        mCategoriesAdapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryDisplayList clickedAssociation) {
                // TODO: Codificar acciones de click en items
            }
        });
        mCategoriesList.setAdapter(mCategoriesAdapter);

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAppointments(categoryFromIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAppointments(getIntent().getExtras().getString("Categoria_Id_Cat"));
    }

    private void loadAppointments(String category) {
        System.out.println("LOAD APPOINTMENTS: Category: " + category);
        showLoadingIndicator(true);

        // Obtener token de usuario, para validar futuras consultas
        //String token = SessionPrefs.get(this).getToken();

        if(category.equals("0")){
            Call<List<CategoryDisplayList>> listCall;
            if(mUneteApi != null){//TODO:validar si no hubo conexion al api
                listCall = mUneteApi.getAll(new AllListBody());
                listCall.enqueue(new Callback<List<CategoryDisplayList>>() {
                    @Override
                    public void onResponse(Call<List<CategoryDisplayList>> call, Response<List<CategoryDisplayList>> response) {
                        if (!response.isSuccessful()) {
                            String error = "Ha ocurrido un error. Contacte al administrador";
                            if (response.errorBody()
                                    .contentType()
                                    .subtype()
                                    .equals("application/json")) {
                                ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                                error = apiError.getMessage();
                                Log.d("CategoryListActivity", apiError.getDeveloperMessage());
                            } else {
                                //error = response.message();
                                try {
                                    // Reportar causas de error no relacionado con la API
                                    Log.d("CategoryListActivity", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            showLoadingIndicator(false);
                            showErrorMessage(error);
                            return;
                        }

                        //TODO: catch excepption SQL not result
                        if (response.body() != null){
                            showLoadingIndicator(false);
                            List<CategoryDisplayList> serverAppointments = response.body();
                            System.out.println(serverAppointments);//
                            showAppointments(serverAppointments);
                        } else {
                            showLoadingIndicator(false);
                            showErrorMessage("No existen registros");
                            //TODO:que hacer si no hay registros??
                            CategoryListActivity.this.finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CategoryDisplayList>> call, Throwable t) {
                        showLoadingIndicator(false);
                        Log.d(TAG, "Peticion rechazada:" + t.getMessage());
                        showErrorMessage("Error de comunicacion");
                    }
                });
            }else {
                System.out.print("Pelana no tienes conexión a tu api");
            }
        }else if(!category.equals("0")) {
            Call<List<CategoryDisplayList>> listCall;
            if(mUneteApi != null){//TODO:validar si no hubo conexion al api
                listCall = mUneteApi.getByCategory(new CategoryListBody(category));
                listCall.enqueue(new Callback<List<CategoryDisplayList>>() {
                    @Override
                    public void onResponse(Call<List<CategoryDisplayList>> call, Response<List<CategoryDisplayList>> response) {
                    if (!response.isSuccessful()) {
                            String error = "Ha ocurrido un error. Contacte al administrador";
                            if (response.errorBody()
                                    .contentType()
                                    .subtype()
                                    .equals("application/json")) {
                                ApiError apiError = ApiError.fromResponseBody(response.errorBody());

                                error = apiError.getMessage();
                                Log.d("CategoryListActivity", apiError.getDeveloperMessage());
                            } else {
                                //error = response.message();
                                try {
                                    // Reportar causas de error no relacionado con la API
                                    Log.d("CategoryListActivity", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            showLoadingIndicator(false);
                            showErrorMessage(error);
                            return;
                        }
                        //TODO: catch excepption SQL not result
                        if (response.body() != null){
                            showLoadingIndicator(false);
                            List<CategoryDisplayList> serverAppointments = response.body();
                            System.out.println(serverAppointments);//
                            showAppointments(serverAppointments);
                        } else {
                            showLoadingIndicator(false);
                            showErrorMessage("No existen registros");
                            //TODO:que hacer si no hay registros??
                            CategoryListActivity.this.finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CategoryDisplayList>> call, Throwable t) {
                        showLoadingIndicator(false);
                        Log.d(TAG, "Peticion rechazada:" + t.getMessage());
                        showErrorMessage("Error de comunicacion");
                    }
                });
            }else {
                System.out.print("Pelana no tienes conexión a tu api");
            }
        }
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
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void showAppointments(List<CategoryDisplayList> serverAppointments) {
        mCategoriesAdapter.swapItems(serverAppointments);
        mCategoriesList.setVisibility(View.VISIBLE);
    }

}
