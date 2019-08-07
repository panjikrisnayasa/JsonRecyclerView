package com.panjikrisnayasa.jsonrecyclerview

import android.os.Parcel
import android.os.Parcelable

data class Earthquake constructor(var magnitude: String = "", var location: String = "", var date: String = "") :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(magnitude)
        parcel.writeString(location)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Earthquake> {
        override fun createFromParcel(parcel: Parcel): Earthquake {
            return Earthquake(parcel)
        }

        override fun newArray(size: Int): Array<Earthquake?> {
            return arrayOfNulls(size)
        }
    }
}