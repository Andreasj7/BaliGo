package com.example.baligo

import android.os.Parcel
import android.os.Parcelable

data class Recommendation(
    val name: String,
    val photoUrl: String,
    val googleMapsUrl: String,
    val rating: Float,
    val reviewUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(photoUrl)
        parcel.writeString(googleMapsUrl)
        parcel.writeFloat(rating)
        parcel.writeString(reviewUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recommendation> {
        override fun createFromParcel(parcel: Parcel): Recommendation {
            return Recommendation(parcel)
        }

        override fun newArray(size: Int): Array<Recommendation?> {
            return arrayOfNulls(size)
        }
    }
}
