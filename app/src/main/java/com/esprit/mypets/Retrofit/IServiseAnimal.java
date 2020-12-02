package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;
import com.esprit.mypets.entyityResponse.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiseAnimal {


    @POST("animal/add")
    Call<AnimalResponse> registerUser(@Body Animal animal);

    @POST("animal/get")
    Call<AnimalResponse> GetAnimalbyId(@Body Animal animal);

    @POST("animal/list")
    Call<ArrayList<Animal>> GetAllAnimal();

}
