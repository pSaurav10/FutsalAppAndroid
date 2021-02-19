package com.example.futsalapp.repository

import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.api.UserAPI
import com.example.futsalapp.model.User
import com.example.futsalapp.response.LoginSignupResponse

class UserRepository: MyApiRequest() {
    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //register user
    suspend fun userRegister(user: User): LoginSignupResponse {
        return apiRequest {
            userAPI.registerPlayer(user)
        }
    }

    //login user
    suspend fun checkUser(username:String,password:String):LoginSignupResponse{
        return apiRequest {
            userAPI.checkUser(username,password)
        }
    }
}