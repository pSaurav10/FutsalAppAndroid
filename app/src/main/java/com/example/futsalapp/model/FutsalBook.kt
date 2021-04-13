package com.example.futsalapp.model

import androidx.room.PrimaryKey

data class FutsalBook (
        @PrimaryKey
        var _id: String = "",
        var futsalname: String? = null,
        var futsalid: String? = null,
        var date: String? = null,
        var time: String? = null,
        var username: String? = null,
        var userid: String? = null
)