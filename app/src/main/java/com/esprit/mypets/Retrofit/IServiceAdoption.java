package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.Adoption;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiceAdoption {

    @POST("adoption/add")
    Call<VolontairesResponse> AddAdoption(@Body Adoption adoption);

    @POST("adoption/get")
    Call<VolontairesResponse> GetAdoptionlbyId(@Body Adoption adoption);

}
