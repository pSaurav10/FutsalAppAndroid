package com.example.futsalapp.repository

import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.api.UserAPI
import com.example.futsalapp.response.LoginSignupResponse

class UserRepository: MyApiRequest() {
    private val userAPI =
            ServiceBuilder.buildService(UserAPI::class.java)

    //login user
    suspend fun userLogin(username:String, password:String): LoginSignupResponse {
        return apiRequest {
            userAPI.loginPlayer(username, password)
        }
    }


}