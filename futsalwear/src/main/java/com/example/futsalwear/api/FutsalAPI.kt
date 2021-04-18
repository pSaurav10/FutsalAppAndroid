package com.example.futsalapp.api


import com.example.futsalapp.response.AllFutsalResponse
import retrofit2.Response
import retrofit2.http.*

interface FutsalAPI {
    @GET("futsal/fetch")
    suspend fun getAllFutsal(
            @Header("Authorization") token: String,
    ): Response<AllFutsalResponse>

}