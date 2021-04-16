package com.example.futsalapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.ArrayList

@Entity
data class Post(
        @PrimaryKey
        var _id: String = "",
        var post: String? = null,
        var username: String? = null,
        var userimage: String? = null,
        var createdAt: String? = null,
        var userId:  String?= null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString()!!,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(_id)
                parcel.writeString(post)
                parcel.writeString(username)
                parcel.writeString(userimage)
                parcel.writeString(createdAt)
                parcel.writeString(userId)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Post> {
                override fun createFromParcel(parcel: Parcel): Post {
                        return Post(parcel)
                }

                override fun newArray(size: Int): Array<Post?> {
                        return arrayOfNulls(size)
                }
        }
}