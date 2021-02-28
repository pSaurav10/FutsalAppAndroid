package com.example.futsalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.Field

data class Futsal (
        var _id: String,
        var name: String? = null,
        var address: String? = null,
        var phoneNumber: String? = null,
        var description: String? = null,
        var image: String? = null,
)
