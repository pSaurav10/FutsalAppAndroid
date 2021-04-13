package com.example.futsalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post (
        @PrimaryKey
        var _id: String = "",
        var post: String? = null,
        var username: String? = null,
        var userimage: String? = null,
        var createdAt: String? = null,
        var comments: MutableList<Comment>? = null,
        var userId:  String?= null
)