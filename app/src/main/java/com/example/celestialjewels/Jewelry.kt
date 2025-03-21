package com.example.celestialjewels

import android.os.Parcel
import android.os.Parcelable

data class Jewelry(
    val id: Int,
    val name: String,
    val price: Double,
    val imageResource: Int,
    var quantity: Int = 1 // Default quantity is 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt() // Read quantity from Parcel
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeInt(imageResource)
        parcel.writeInt(quantity) // Write quantity to Parcel
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Jewelry> {
        override fun createFromParcel(parcel: Parcel): Jewelry = Jewelry(parcel)
        override fun newArray(size: Int): Array<Jewelry?> = arrayOfNulls(size)
    }
}
