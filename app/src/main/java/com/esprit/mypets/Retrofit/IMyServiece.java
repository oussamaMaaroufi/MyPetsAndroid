package com.esprit.mypets.Retrofit;


import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyServiece {
    @POST("users/register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("password")String password,
                                    @Field("type") String type);

    @POST("users/auth")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                     @Field("password")String password);




}
