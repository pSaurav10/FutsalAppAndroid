package com.example.futsalapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class FutsalItem (
        @PrimaryKey(autoGenerate= true)
        var uid: Int = 0,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name = "address")
        var address: String? = null,

        @ColumnInfo(name = "phoneNumber")
        var phoneNumber: String? = null,

        @ColumnInfo(name = "description")
        var description: String? = null,

        @ColumnInfo(name = "image")
        var image: String? = null,
)