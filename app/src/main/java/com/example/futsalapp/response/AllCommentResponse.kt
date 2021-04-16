package com.example.futsalapp.response

import com.example.futsalapp.model.Comment

data class AllCommentResponse (
        val success: Boolean? = null,
        val data: MutableList<Comment>? = null
        )