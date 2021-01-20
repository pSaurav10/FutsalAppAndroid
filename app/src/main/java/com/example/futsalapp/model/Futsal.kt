package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable

data class Futsal (
    val name: String? = null,
    val image: String? = null
): Parcelable {
    constructor(parcel: Parcel): this(
            parcel.readString(),
            parcel.readString()
    ){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
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
