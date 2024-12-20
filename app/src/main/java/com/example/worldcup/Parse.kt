package com.example.worldcup

class Parse {
    fun parseData(line:String):Match{
        var list=line.split(",")
        var year=list[Index.YEAR]
        var dateTime=list[Index.DATE_TIME]
        var stadium=list[Index.STADIUM]
        var city=list[Index.CITY]
        var homeTeamName=list[Index.HOME_TEAM_NAME]
        var homeTeamGoals=list[Index.HOME_TEAM_GOALS]
        var awayTeamName=list[Index.AWAY_TEAM_NAME]
        var awayTeamGoals=list[Index.AWAY_TEAM_GOALS]
        var stage=list[Index.STAGE]
        return Match(
            year = year,
            date_time = dateTime,
        stadium =stadium, city = city,
        home_team_name = homeTeamName,
        home_team_goals = homeTeamGoals,
        away_team_name = awayTeamName,
        away_team_goals = awayTeamGoals,
        Stage =stage)
    }

}