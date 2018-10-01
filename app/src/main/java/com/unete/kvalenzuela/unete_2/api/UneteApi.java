package com.unete.kvalenzuela.unete_2.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UneteApi {

    // TODO: Cambiar host por "10.0.3.2" para Genymotion.
    // TODO: Cambiar host por "10.0.2.2" para AVD.
    // TODO: Cambiar host por IP de tu PC para dispositivo real.
    //public static final String BASE_URL = "http://192.168.1.68/api.unete.com/v1/";
    public static final String BASE_URL = "http://www.uneac.org.mx/UNE/api.unete.com/v1/";



    @POST("asociacion/login")
    Call<Asociacion> login(@Body LoginBody loginBody);

    @POST("asociacion/register")
    Call<Asociacion> signup(@Body SignupBody signupBody);

    @PUT("asociacion/update")
    Call<Asociacion> update(@Body ProfileBody profileBody);
}
