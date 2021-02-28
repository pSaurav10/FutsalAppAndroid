package com.example.futsalapp.repository

import com.example.futsalapp.api.FutsalAPI
import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.response.AllFutsalResponse

class FutsalRepository() : MyApiRequest() {
    private val futsalAPI =
            ServiceBuilder.buildService(FutsalAPI::class.java)

    suspend fun getAllFutsal(): AllFutsalResponse {
        return apiRequest {
            futsalAPI.getAllFutsal(
                    ServiceBuilder.token!!
            )
        }
    }

}