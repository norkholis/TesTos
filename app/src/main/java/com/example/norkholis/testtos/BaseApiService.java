package com.example.norkholis.testtos;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by norkholis on 02/02/18.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ListUser>loginRequest(@Field("username")String username,
                                 @Field("password")String password);

    @GET("kendaraan")
    Call <List<Kendaraan>>dataKendaraan(@Query("_token")String token);
}
