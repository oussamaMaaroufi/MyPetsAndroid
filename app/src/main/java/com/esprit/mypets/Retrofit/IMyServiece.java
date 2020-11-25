package com.esprit.mypets.Retrofit;


import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.UserResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyServiece {

    @POST("users/register")
    Call<UserResponse> registerUser(@Body User user);
    //@Body: encapsulate what we want to send to the server
    @POST("users/auth")
    Call<UserResponse> loginUser(@Body User user);




}
