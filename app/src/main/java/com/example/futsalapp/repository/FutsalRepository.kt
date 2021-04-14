package com.example.futsalapp.repository

import android.content.Context
import com.example.futsalapp.api.FutsalAPI
import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.model.FutsalBook
import com.example.futsalapp.model.Player
import com.example.futsalapp.response.AllBookingResponse
import com.example.futsalapp.response.AllFutsalResponse
import com.example.futsalapp.response.LoginSignupResponse

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

    suspend fun bookFutsal(futsalbook: FutsalBook): AllBookingResponse {
        return apiRequest {
            futsalAPI.futsalBook(futsalbook)
        }
    }

    suspend fun insertFutsal(context: Context, futsal : List<Futsal>){
        FutsalDB.getInstance(context).getFutsalDao().DeleteAllFutsal()
        FutsalDB.getInstance(context).getFutsalDao().insertFutsal(futsal)
    }

    suspend fun getAllFutsal(context: Context) : MutableList<Futsal>{
        return FutsalDB.getInstance(context).getFutsalDao().getallFutsal()
    }

}