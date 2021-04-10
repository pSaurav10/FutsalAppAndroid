package com.example.futsalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
        @PrimaryKey
        var _id: String = "",
        var name: String? = null,
        var description: String? = null,
        var image: String? = null,
        var date: String? = null,
        var fee: String? = null,
        var phone: String? = null,
        var location: String? = null,
        var userId: String? = null
)