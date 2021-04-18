package com.example.futsalapp.api

import com.example.futsalapp.response.*
import com.example.futsalwear.Model.Player
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    //Login user
    @FormUrlEncoded
    @POST("player/login")
    suspend fun loginPlayer(
            @Field("username") username: String,
            @Field("password") password: String
    ): Response<LoginSignupResponse>
}