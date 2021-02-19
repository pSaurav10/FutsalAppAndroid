package com.example.futsalapp.api

import com.example.futsalapp.model.Player
import com.example.futsalapp.response.LoginSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    @POST("player/register")
    suspend fun registerPlayer(
        @Body player: Player
    ): Response<LoginSignupResponse>

    //Login user
    @FormUrlEncoded
    @POST("player/login")
    suspend fun loginPlayer(
            @Field("username") username: String,
            @Field("password") password: String
    ): Response<LoginSignupResponse>

}