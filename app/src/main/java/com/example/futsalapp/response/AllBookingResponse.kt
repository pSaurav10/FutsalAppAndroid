package com.example.futsalapp.response

import com.example.futsalapp.model.FutsalBook

data class AllBookingResponse (
    val success: Boolean? = null,
    val data: MutableList<FutsalBook>? = null
)