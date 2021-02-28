package com.example.futsalapp.dao

import androidx.room.*
import com.example.futsalapp.entity.FutsalItem

@Dao
interface FutsalDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(futsals: List<FutsalItem>)

    @Query("select * from FutsalItem")
    suspend fun getallFutsal() : List<FutsalItem>

    @Query("SELECT * FROM FutsalItem where name = :name")
    suspend fun getFutsal(name: String): FutsalItem
}