package com.example.futsalapp.model

import androidx.room.Entity

data class Player(
        var fname: String? = null,
        var lname: String? = null,
        var username: String? = null,
        var password: String? = null,
        var address: String? = null,
        var phone: String? = null,
        var email: String? = null,
)