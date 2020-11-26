package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServeceAbri {


    @POST("abri/add")
    Call<VolontairesResponse> registerAbri(@Body Abris abris);

    @POST("abri/get")
    Call<VolontairesResponse> GetAbrilbyId(@Body Abris abris);
}