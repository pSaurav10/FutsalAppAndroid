package com.example.futsalapp.api

import com.example.futsalapp.response.AllCommentResponse
import com.example.futsalapp.response.AllPostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PostAPI {
    @GET("post/fetch")
    suspend fun getAllPost(
            @Header("Authorization") token: String,
    ): Response<AllPostResponse>

    @GET("post/comments/{id}")
    suspend fun getPostComment(
            @Header("Authorization") token: String,
            @Path("id") id: String
    ): Response<AllCommentResponse>
}