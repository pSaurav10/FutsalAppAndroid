package com.example.futsalapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.futsalapp.dao.UserDAO
import com.example.futsalapp.model.User

@Database(
    entities = [(User::class)],
    version = 1
)
abstract class UserDB: RoomDatabase() {
    abstract fun getUserDao(): UserDAO
    companion object{
        @Volatile
        private var instance: UserDB? = null
        fun getInstance(context: Context): UserDB{
            if(instance == null){
                synchronized(UserDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java,
                "UserDB"
            ).build()
    }
}