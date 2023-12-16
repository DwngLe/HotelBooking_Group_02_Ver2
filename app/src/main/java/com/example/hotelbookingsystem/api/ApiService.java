package com.example.hotelbookingsystem.api;

import com.example.hotelbookingsystem.model.Profile;
import com.example.hotelbookingsystem.model.Registration;
import com.example.hotelbookingsystem.model.RegistrationResponse;
import com.example.hotelbookingsystem.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
//    String url = "http://172.27.0.1:8080/"; // ip lê dương
    String url = "http://10.20.5.242:8080/";// ip hhd
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
    Call<RegistrationResponse> registerAccount(@Body Registration registration);

    @GET("admin/search")
    Call<UserResponse> getProfile(@Query("username") String username);

    @GET("admin/searchByLastname")
    Call<UserResponse> getUserByLastName(@Query("lastname") String lastname);

    @PUT("admin/update")
    Call<Profile> updateUser(@Body Profile profile);

    @DELETE("admin/delete")
    Call<Void> deleteUser(@Query("id") Long id);


}
