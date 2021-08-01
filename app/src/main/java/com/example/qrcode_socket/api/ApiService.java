package com.example.qrcode_socket.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Author : Ryans
 * Date : 2021/7/30
 * Introduction :
 */
public interface ApiService {

    @POST("/api/authenticate")
    Call<Map<String, String>> login(@Body User user);

    @GET("/api/user")
    Call<Map<String, Object>> getUser(@Header("Authorization")String Authorization);

    @GET("/api/qr")
    Call<Object> qrCodeMsg(@Header("Authorization")String Authorization, @Query("msg") String msg);
}
