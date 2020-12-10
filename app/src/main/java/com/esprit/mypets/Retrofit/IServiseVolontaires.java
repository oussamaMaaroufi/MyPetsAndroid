package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entity.Volontaires;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiseVolontaires {


    @POST("volontaire/add")
    Call<VolontairesResponse> registerVeterinaires(@Body Volontaires volontaires);

    @POST("volontaire/auth")
    Call<VolontairesResponse> GetVeterinairesbyId(@Body Volontaires volontaires);
}
