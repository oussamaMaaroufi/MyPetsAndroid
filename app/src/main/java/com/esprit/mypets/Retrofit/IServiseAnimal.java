package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;
import com.esprit.mypets.entyityResponse.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiseAnimal {


    @POST("animal/add")
    Call<AnimalResponse> AddAnimal(@Body Animal animal);

    @POST("animal/get")
    Call<AnimalResponseList> GetAnimalbyIdUser(@Body User user);

    @POST("animal/getById")
    Call<AnimalResponse> GetAnimalbyId(@Body Animal animal);


    @POST("animal/list")
    Call<AnimalResponseList> GetAllAnimal();

    @POST("animal//delete")
    Call<AnimalResponse> DeleteAnimal(@Body Animal animal);

}
