package com.example.futsalapp.api

import com.example.futsalapp.model.Player
import com.example.futsalapp.response.AllFutsalResponse
import com.example.futsalapp.response.LoginSignupResponse
import retrofit2.Response
import retrofit2.http.*

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

    @GET("player/fetch")
    suspend fun getAllPlayer(
            @Header("Authorization") token: String,
    ): Response<LoginSignupResponse>

}