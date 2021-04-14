package com.example.futsalapp.api

import com.example.futsalapp.model.FutsalBook
import com.example.futsalapp.model.Player
import com.example.futsalapp.response.AllBookingResponse
import com.example.futsalapp.response.AllFutsalResponse
import com.example.futsalapp.response.LoginSignupResponse
import retrofit2.Response
import retrofit2.http.*

interface FutsalAPI {
    @GET("futsal/fetch")
    suspend fun getAllFutsal(
            @Header("Authorization") token: String,
    ): Response<AllFutsalResponse>


    @POST("futsalbook")
    suspend fun futsalBook(
            @Body futsalbook: FutsalBook
    ): Response<AllBookingResponse>
}