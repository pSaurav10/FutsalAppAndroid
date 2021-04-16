package com.example.futsalapp.api

import com.example.futsalapp.model.Comment
import com.example.futsalapp.model.Post
import com.example.futsalapp.response.AllCommentResponse
import com.example.futsalapp.response.AllPostResponse
import retrofit2.Response
import retrofit2.http.*

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

    @POST("post/add")
    suspend fun addPost(
        @Body post: Post,
        @Header("Authorization") token: String
    ): Response<AllPostResponse>

    @PUT("comment/add")
    suspend fun addComment(
        @Body comment: Comment,
        @Header("Authorization") token: String
    ): Response<AllCommentResponse>
}