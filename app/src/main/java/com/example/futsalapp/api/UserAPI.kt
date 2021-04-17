package com.example.futsalapp.api

import com.example.futsalapp.model.Player
import com.example.futsalapp.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @GET("/profile")
    suspend fun getPlayer(
            @Header("Authorization") token: String,
    ): Response<UserResponse>

    @PUT("profile/update")
    suspend fun updateProfile(
        @Body player: Player,
        @Header("Authorization") token: String,
    ): Response<UserUpdateResponse>

    @Multipart
    @PUT("profile/photo/{id}")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>

}