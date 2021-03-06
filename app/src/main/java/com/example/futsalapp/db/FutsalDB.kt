package com.example.futsalapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.futsalapp.dao.EventDAO
import com.example.futsalapp.dao.FutsalDAO
import com.example.futsalapp.model.Event
import com.example.futsalapp.model.Futsal

@Database(
        entities = [(Futsal::class), (Event::class)],
        version = 1,
        exportSchema=false
)
abstract class FutsalDB: RoomDatabase() {
    abstract fun getFutsalDao(): FutsalDAO
    abstract fun getEventDao(): EventDAO
    companion object{
        @Volatile
        private var instance: FutsalDB? = null

        fun getInstance(context: Context): FutsalDB{
            if (instance == null){
                synchronized(FutsalDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        FutsalDB::class.java,
                        "FutsalDB"
                ).build()

    }
}