package com.example.futsalapp.response

import com.example.futsalapp.model.Futsal

data class AllFutsalResponse (
        val success: Boolean? = true,
        val data: MutableList<Futsal>? = null
        )