package com.example.searchapp.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BlocksModel(

    @field:SerializedName("BlocksModel")
    val blocks: List<BlocksItem?>? = null
)

data class BlocksItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("units")
    var units: List<UnitsItem?>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(UnitsItem.CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(units)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BlocksItem> {
        override fun createFromParcel(parcel: Parcel): BlocksItem {
            return BlocksItem(parcel)
        }

        override fun newArray(size: Int): Array<BlocksItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class UnitsItem(

    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("activities")
    val activities: List<ActivitiesItem?>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(ActivitiesItem.CREATOR)
    ) {
    }
    override fun toString(): String {
        return name.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(activities)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnitsItem> {
        override fun createFromParcel(parcel: Parcel): UnitsItem {
            return UnitsItem(parcel)
        }

        override fun newArray(size: Int): Array<UnitsItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class ActivitiesItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("totalDays")
    val totalDays: Int? = null,

    @field:SerializedName("stepName")
    val stepName: String? = null,

    @field:SerializedName("completionStatus")
    val completionStatus: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun toString(): String {
        return name.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(totalDays)
        parcel.writeString(stepName)
        parcel.writeValue(completionStatus)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ActivitiesItem> {
        override fun createFromParcel(parcel: Parcel): ActivitiesItem {
            return ActivitiesItem(parcel)
        }

        override fun newArray(size: Int): Array<ActivitiesItem?> {
            return arrayOfNulls(size)
        }
    }
}
