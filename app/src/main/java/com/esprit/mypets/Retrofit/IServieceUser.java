package com.esprit.mypets.Retrofit;


import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IServieceUser {
        @POST("users/register")
        Call<UserResponse> registerUser(@Body User user);

        @POST("users/auth")
        Call<UserResponse> loginUser(@Body User user);




}
