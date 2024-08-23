import android.os.Parcel
import android.os.Parcelable

data class Recommendation(
    val name: String,
    val link: String,
    val category: String,
    val photoUrl: String,
    val review: String,
    val rating: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(link)
        parcel.writeString(category)
        parcel.writeString(photoUrl)
        parcel.writeString(review)
        parcel.writeFloat(rating)
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
