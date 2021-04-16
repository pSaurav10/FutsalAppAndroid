package com.example.futsalapp.repository

import android.content.Context
import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.PostAPI
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.model.Post
import com.example.futsalapp.response.AllCommentResponse
import com.example.futsalapp.response.AllPostResponse

class PostRepository(): MyApiRequest() {
    private val postAPI =
            ServiceBuilder.buildService(PostAPI::class.java)

    suspend fun getAllPost(): AllPostResponse{
        return apiRequest {
            postAPI.getAllPost(
                    ServiceBuilder.token!!
            )
        }
    }

    suspend fun addPost(post: Post): AllPostResponse {
        return apiRequest{
            postAPI.addPost(
                post,
                ServiceBuilder.token!!
            )
        }
    }

    suspend fun getAllComment(id: String): AllCommentResponse {
        return apiRequest {
            postAPI.getPostComment(
                    ServiceBuilder.token!!, id
            )
        }
    }

}