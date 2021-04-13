package com.example.futsalapp.response

import com.example.futsalapp.model.Event

data class AllEventResponse (
        val success: Boolean? = true,
        val data: MutableList<Event>? = null
)