package com.example.futsalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Futsal (
        @PrimaryKey
        var _id: String = "",
        var name: String? = null,
        var address: String? = null,
        var phoneNumber: String? = null,
        var description: String? = null,
        var image: String? = null,
)
