package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
        @PrimaryKey
        var _id: String = "",
        var name: String? = null,
        var description: String? = null,
        var image: String? = null,
        var date: String? = null,
        var fee: String? = null,
        var phone: String? = null,
        var location: String? = null,
        var userId: String? = null
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
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(_id)
                parcel.writeString(name)
                parcel.writeString(description)
                parcel.writeString(image)
                parcel.writeString(date)
                parcel.writeString(fee)
                parcel.writeString(phone)
                parcel.writeString(location)
                parcel.writeString(userId)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Event> {
                override fun createFromParcel(parcel: Parcel): Event {
                        return Event(parcel)
                }

                override fun newArray(size: Int): Array<Event?> {
                        return arrayOfNulls(size)
                }
        }
}