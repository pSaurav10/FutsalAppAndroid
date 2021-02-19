package com.example.futsalapp.api

import com.example.futsalapp.model.User
import com.example.futsalapp.response.LoginSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    @POST("/player/register")
    suspend fun registerPlayer(
        @Body user: User
    ): Response<LoginSignupResponse>

    //Login user
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun checkUser(
            @Field("username") username : String,
            @Field("password") password : String
    ): Response<LoginSignupResponse>

}