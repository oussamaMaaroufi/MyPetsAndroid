package com.esprit.mypets.Retrofit;

import com.esprit.mypets.entity.LostAndFound;
import com.esprit.mypets.entyityResponse.LostFoundResponse;
import com.esprit.mypets.entyityResponse.LostFoundResponseList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface IServiceLAF {

    @POST("lostAndFound/add")
    Call<LostFoundResponse> AddlostAndfound(LostAndFound lostAndFound);

    @POST("lostAndFound/getLost")
    Call<LostFoundResponseList> GetAllLost();

    @POST("lostAndFound/getFound")
    Call<LostFoundResponseList> GetAllFound();

}
