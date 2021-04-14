package com.example.futsalapp.response

import com.example.futsalapp.model.Player

data class UserResponse (
        val success : Boolean? = null,
        val data: Player? = null
)