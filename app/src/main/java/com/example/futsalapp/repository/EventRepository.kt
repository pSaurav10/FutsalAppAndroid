package com.example.futsalapp.repository

import android.content.Context
import com.example.futsalapp.api.EventAPI
import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.model.Event
import com.example.futsalapp.response.AllEventResponse

class EventRepository() : MyApiRequest() {
    private val eventAPI =
            ServiceBuilder.buildService(EventAPI::class.java)

    suspend fun getAllFutsal(): AllEventResponse {
        return apiRequest {
            eventAPI.getAllEvent(
                    ServiceBuilder.token!!
            )
        }
    }

    suspend fun insertEvent(context: Context, event : List<Event>){
        FutsalDB.getInstance(context).getEventDao().DeleteAllEvent()
        FutsalDB.getInstance(context).getEventDao().insertEvent(event)
    }

    suspend fun getAllEvent(context: Context) : MutableList<Event>{
        return FutsalDB.getInstance(context).getEventDao().getallEvent()
    }

}