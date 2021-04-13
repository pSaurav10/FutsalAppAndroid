package com.example.futsalapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.futsalapp.model.Event

@Dao
interface EventDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(events: List<Event>)

    @Query("select * from Event")
    suspend fun getallEvent() : MutableList<Event>

//    @Query("SELECT * FROM EventItem where name = :name")
//    suspend fun getEvent(name: String): EventItem

    @Query("DELETE FROM Event")
    suspend fun DeleteAllEvent()
}