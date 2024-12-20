package com.example.worldcup

import java.io.Serializable

data class Stats(
    var teamName:String,
    var matchPlayed:Int,
    var matchWon:Int,
    var matchLost:Int,
    var matchDraw:Int,
    var worldCup:Int
):Serializable {
}