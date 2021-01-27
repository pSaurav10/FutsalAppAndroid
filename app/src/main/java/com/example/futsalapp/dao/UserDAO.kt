package com.example.futsalapp.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.futsalapp.model.User

@Dao
interface UserDAO {
    @Insert
    suspend fun registerUser(user: User)
}