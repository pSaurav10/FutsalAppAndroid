package com.example.futsalapp.response

import com.example.futsalapp.model.Futsal

data class AllFutsalResponse (
        val success: Boolean? = true,
        val count: Int? = 0,
        val data: ArrayList<Futsal>? = null
        )