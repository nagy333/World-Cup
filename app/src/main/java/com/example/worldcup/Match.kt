package com.example.worldcup

import android.os.Parcel
import android.os.Parcelable

data class Match(
    var year: String?,
    var date_time: String?,
    var stadium:String?,
    var city:String?,
    var home_team_name:String?,
    var home_team_goals: String?,
    var away_team_name:String?,
    var away_team_goals: String?,
    var Stage:String?

) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
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
        parcel.writeString(year)
        parcel.writeString(date_time)
        parcel.writeString(stadium)
        parcel.writeString(city)
        parcel.writeString(home_team_name)
        parcel.writeString(home_team_goals)
        parcel.writeString(away_team_name)
        parcel.writeString(away_team_goals)
        parcel.writeString(Stage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}