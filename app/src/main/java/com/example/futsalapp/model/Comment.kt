package com.example.futsalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment (
        @PrimaryKey
        var _id: String = "",
        var comment: String? = null,
        var cusername: String? = null,
        var cuserimage: String? = null,
        var ccreatedAt: String? = null,
        var cuserid: String? = null,
        var id: String? = null
)