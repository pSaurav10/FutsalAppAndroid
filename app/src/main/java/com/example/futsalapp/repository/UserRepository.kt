package com.example.futsalapp.repository

import com.example.futsalapp.api.MyApiRequest
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.api.UserAPI
import com.example.futsalapp.model.Player
import com.example.futsalapp.response.*

class UserRepository: MyApiRequest() {
    private val userAPI =
            ServiceBuilder.buildService(UserAPI::class.java)

    //register user
    suspend fun userRegister(player: Player): LoginSignupResponse {
        return apiRequest {
            userAPI.registerPlayer(player)
        }
    }

    //login user
    suspend fun userLogin(username:String, password:String):LoginSignupResponse {
        return apiRequest {
            userAPI.loginPlayer(username, password)
        }
    }

    suspend fun getUser(): UserResponse {
        return apiRequest {
            userAPI.getPlayer(
                    ServiceBuilder.token!!
            )
        }
    }

    suspend fun updateProfile(player: Player): UserUpdateResponse {
        return apiRequest {
            userAPI.updateProfile(
                player, ServiceBuilder.token!!
            )
        }
    }

}