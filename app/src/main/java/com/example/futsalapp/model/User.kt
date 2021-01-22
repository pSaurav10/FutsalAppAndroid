package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable

data class User(
        var imagepp: String? = null,
        var fname: String? = null,
        var lname: String? = null,
        var username: String? = null,
        var password: String? = null,
        var address: String? = null,
        var email: String? = null,
        var phone: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagepp)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(address)
        parcel.writeString(email)
        parcel.writeString(phone)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}