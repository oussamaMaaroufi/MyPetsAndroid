package com.esprit.mypets.Retrofit;


import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.ResponseClass;
import com.esprit.mypets.entyityResponse.UserResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IServieceUser {

        @POST("users/register")
        Call<UserResponse> registerUser(@Body User user);

        @POST("users/auth")
        Call<UserResponse> loginUser(@Body User user);

        @POST("users/getuser")
        Call<UserResponse> getUserbyID(@Body User user);

        @POST("uploadfile")
        @Multipart
        Call<ResponseClass> UploadImage (@Part MultipartBody.Part file);







}
