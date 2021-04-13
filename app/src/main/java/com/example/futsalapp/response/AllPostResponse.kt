package com.example.futsalapp.response

import com.example.futsalapp.model.Post

data class AllPostResponse (
        val success: Boolean? = true,
        val data: MutableList<Post>? = null
)