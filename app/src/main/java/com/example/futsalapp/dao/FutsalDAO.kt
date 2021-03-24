package com.example.futsalapp.dao

import androidx.room.*
import com.example.futsalapp.model.Futsal

@Dao
interface FutsalDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFutsal(futsals: List<Futsal>)

    @Query("select * from Futsal")
    suspend fun getallFutsal() : MutableList<Futsal>

//    @Query("SELECT * FROM FutsalItem where name = :name")
//    suspend fun getFutsal(name: String): FutsalItem

    @Query("DELETE FROM Futsal")
    suspend fun DeleteAllFutsal()
}