package com.example.futsalapp.repository

import android.content.Context
import com.example.futsalapp.api.FutsalAPI
import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.model.Futsal
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

    suspend fun insertFutsal(context: Context, futsal : List<Futsal>){
        FutsalDB.getInstance(context).getFutsalDao().DeleteAllFutsal()
        FutsalDB.getInstance(context).getFutsalDao().insertFutsal(futsal)
    }

    suspend fun getAllFutsal(context: Context) : MutableList<Futsal>{
        return FutsalDB.getInstance(context).getFutsalDao().getallFutsal()
    }

}