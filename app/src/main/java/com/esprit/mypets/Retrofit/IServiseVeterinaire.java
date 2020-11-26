package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entyityResponse.VeterinairesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiseVeterinaire {


    @POST("veterinaires/add")
    Call<VeterinairesResponse> registerVeterinaires(@Body Veterinaires veterinaires);

    @POST("veterinaires/get")
    Call<VeterinairesResponse> GetVeterinairesById(@Body Veterinaires veterinaires);
}
