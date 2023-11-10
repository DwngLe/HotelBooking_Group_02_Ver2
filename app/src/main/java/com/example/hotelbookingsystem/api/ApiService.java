package com.example.hotelbookingsystem.api;

import com.example.hotelbookingsystem.model.Profile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String url = "http://10.20.94.131:8080/";
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyy")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("auth")
    Call<Profile> checkLogin(@Body Profile profile);

    @POST("auth/register")
    Call<Boolean> registerAccount(@Body Profile profile);
}
