package com.example.futsalapp.model

import androidx.room.Entity

@Entity
data class Futsal (
    var name: String? = null,
    var address: String? = null,
    var phoneNumber: String? = null,
    var description: String? = null,
    var image: String? = null,
    var review: ArrayList<String>? = null,
    var grounds: ArrayList<String>? = null,
)
