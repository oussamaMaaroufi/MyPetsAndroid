package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entyityResponse.VeterinaireResponseList;
import com.esprit.mypets.entyityResponse.VeterinairesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServiseVeterinaire {


    @POST("veterinaire/add")
    Call<VeterinairesResponse> registerVeterinaires(@Body Veterinaires veterinaires);

    @POST("veterinaire/auth")
    Call<VeterinairesResponse> GetVeterinairesById(@Body Veterinaires veterinaires);

    @POST("veterinaire/list")
    Call<VeterinaireResponseList> GetVeterinaires();

}
