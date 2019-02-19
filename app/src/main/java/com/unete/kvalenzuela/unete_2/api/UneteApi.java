package com.unete.kvalenzuela.unete_2.api;

import com.unete.kvalenzuela.unete_2.api.model.AllListBody;
import com.unete.kvalenzuela.unete_2.api.model.ApiMessageResponse;
import com.unete.kvalenzuela.unete_2.api.model.ApiResponseCategories;
import com.unete.kvalenzuela.unete_2.api.model.ApiResponseRegisters;
import com.unete.kvalenzuela.unete_2.api.model.Asociacion;
import com.unete.kvalenzuela.unete_2.api.model.AssociationBody;
import com.unete.kvalenzuela.unete_2.api.model.CategoryDisplayList;
import com.unete.kvalenzuela.unete_2.api.model.CategoryListBody;
import com.unete.kvalenzuela.unete_2.api.model.LoginBody;
import com.unete.kvalenzuela.unete_2.api.model.ProfileBody;
import com.unete.kvalenzuela.unete_2.api.model.SignupBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface UneteApi {

    // TODO: Cambiar host por "10.0.3.2" para Genymotion.
    // TODO: Cambiar host por "10.0.2.2" para AVD.
    // TODO: Cambiar host por IP de tu PC para dispositivo real.
    //public static final
    //String BASE_URL = "http://192.168.1.67/api.unete.com/v1/";
    public static final String BASE_URL = "http://www.uneac.org.mx/UNE/api.unete.com/v1/";

    @POST("asociacion/login")
    Call<Asociacion> login(@Body LoginBody loginBody);

    @POST("asociacion/register")
    Call<Asociacion> signup(@Body SignupBody signupBody);

    @PUT("asociacion/update")
    Call<Asociacion> update(@Body ProfileBody profileBody);

    @POST("asociacion/byCategory")
    Call<List<CategoryDisplayList>> getByCategory(@Body CategoryListBody categoryListBody);

    @POST("asociacion/all")
    Call<List<CategoryDisplayList>> getAll(@Body AllListBody allListBody);

    @POST("asociacion/getAffiliate")
    Call<Asociacion> getAffiliate(@Body AssociationBody associationBody);

    @GET("asociacion")
    Call<ApiResponseRegisters> getRegisters(@Header("Authorization") String token,
                                               @QueryMap Map<String, Object> parameters);
    Call<ApiResponseRegisters> getRegisters(@Body AllListBody allListBody);

    @Headers("Content-Type: application/json")
    @PATCH("asociacion/{id}")
    Call<ApiMessageResponse> cancelRegister(@Path("id") int appoitmentId,
                                               @Header("Authorization") String token,
                                               @Body HashMap<String, String> statusMap);

}
