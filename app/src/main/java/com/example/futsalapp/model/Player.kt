package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

data class Player(
        var _id: String = "",
        var fname: String? = null,
        var lname: String? = null,
        var username: String? = null,
        var password: String? = null,
        var address: String? = null,
        var phone: String? = null,
        var email: String? = null,
        var imagepp: String? = null,
        var dob: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(imagepp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}