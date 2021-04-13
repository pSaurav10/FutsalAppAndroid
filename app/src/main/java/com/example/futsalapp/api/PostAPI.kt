package com.example.futsalapp.api

import com.example.futsalapp.response.AllPostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PostAPI {
    @GET("post/fetch")
    suspend fun getAllPost(
            @Header("Authorization") token: String,
    ): Response<AllPostResponse>
}