package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        var imagepp: String? = null,
        var fname: String? = null,
        var lname: String? = null,
        var username: String? = null,
        var password: String? = null,
        var address: String? = null,
        var email: String? = null,
        var phone: String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}

