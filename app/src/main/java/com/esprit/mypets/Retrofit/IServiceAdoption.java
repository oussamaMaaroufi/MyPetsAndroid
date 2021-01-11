package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.Adoption;
import com.esprit.mypets.entyityResponse.AdoptionResponse;
import com.esprit.mypets.entyityResponse.AdoptionResponseList;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiceAdoption {

    @POST("adoption/add")
    Call<AdoptionResponse> AddAdoption(@Body Adoption adoption);

    @POST("adoption/get")
    Call<AdoptionResponse> GetAdoptionById(@Body Adoption adoption);

    @POST("adoption/list")
    Call<AdoptionResponseList> GetAllAdoption();

}
