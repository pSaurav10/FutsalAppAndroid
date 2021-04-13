package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Futsal (
        @PrimaryKey
        var _id: String = "",
        var name: String? = null,
        var address: String? = null,
        var phoneNumber: String? = null,
        var description: String? = null,
        var image: String? = null,
        var grounds: String? = null,
        var fee: String? = null,
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString()!!,
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
                parcel.writeString(name)
                parcel.writeString(address)
                parcel.writeString(phoneNumber)
                parcel.writeString(description)
                parcel.writeString(image)
                parcel.writeString(grounds)
                parcel.writeString(fee)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Futsal> {
                override fun createFromParcel(parcel: Parcel): Futsal {
                        return Futsal(parcel)
                }

                override fun newArray(size: Int): Array<Futsal?> {
                        return arrayOfNulls(size)
                }
        }
}
