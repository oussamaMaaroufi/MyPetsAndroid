package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiseAnimal {


    @POST("animal/add")
    Call<UserResponse> registerUser(@Body Animal animal);

    @POST("animal/get")
    Call<UserResponse> GetAnimalbyId(@Body Animal animal);

    @POST("animal/list")
    Call<UserResponse> GetAllAnimal(@Body Animal animal);

}
