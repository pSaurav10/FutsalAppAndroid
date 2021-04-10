package com.example.futsalapp.api

import com.example.futsalapp.response.AllEventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface EventAPI {
    @GET("event/fetch")
    suspend fun getAllEvent(
            @Header("Authorization") token: String,
    ): Response<AllEventResponse>
}