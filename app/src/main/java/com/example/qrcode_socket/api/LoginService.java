package com.example.qrcode_socket.api;

import retrofit2.Call;
import retrofit2.http.Body;

/**
 * Author : Ryans
 * Date : 2021/7/30
 * Introduction :
 */
public interface LoginService {

    Call<Object> login(@Body User user);
}
