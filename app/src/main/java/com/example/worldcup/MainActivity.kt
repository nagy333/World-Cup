package com.example.worldcup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.worldcup.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var parse: Parse
    lateinit var match: Match
    var index=0
    var fragment=Details_Fragment()
    private lateinit var arrayList:ArrayList<Match>
    private lateinit var teamArrayList:ArrayList<Match>
    private lateinit var statsArrayList:ArrayList<Stats>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var inputStream=assets.open("worldcup.csv")
        val buffer=BufferedReader(InputStreamReader(inputStream))
        arrayList=ArrayList()
        statsArrayList= ArrayList()
        teamArrayList=ArrayList()
        binding.root.setOnClickListener{
            binding.fragmentContainer.visibility= View.VISIBLE
            showDetails()
        }
        buffer.forEachLine {
            parse= Parse()
           match= parse.parseData(it)
            arrayList.add(match)
        }
        binding.addMatch.setOnClickListener{
            var mm=Match("2022","time","stadium","City","Argentina","3","France","3","Final")
            arrayList.add(mm)
        }
        binding.btnList.setOnClickListener{
            var intent=Intent(this@MainActivity,RecyclerActivity::class.java)
            intent.putParcelableArrayListExtra("list",arrayList)
            startActivity(intent)
        }

        data()

        binding.btnNext.setOnClickListener{
            if (index==arrayList.size-1){
                index=0
            }
            index++
            data()
        }
        binding.btnBack.setOnClickListener{
            if (index==0){
                index=arrayList.size-1
            }
            index--
            data()
        }
        binding.btnSearch.setOnClickListener{

            var search=binding.search.text.toString()
            for (i in 0 until (arrayList.size)){
                var M=arrayList[i]
                if (search == M.year)
                    index=arrayList.indexOf(M)
                data()
            }
        }
        binding.searchBtn.setOnClickListener{
            teamArrayList.clear()
            var team=binding.etSearch.text.toString()
            for (i in 0 until (arrayList.size)-1){
                val ma=arrayList[i]
                if(team==ma.home_team_name||team==ma.away_team_name){
                    teamArrayList.add(ma)
                }
            }
           var s= stats(teamArrayList,team)
            var intent=Intent(this@MainActivity,Activity_Search::class.java)
            intent.putExtra("stats",s)
            startActivity(intent)
        }
    }

    private fun showDetails() {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container,fragment)
        transaction.commit()
    }

    fun data(){
        var m=arrayList[index]
        binding.year.text=m.year
        binding.homeTeam.text=m.home_team_name
        binding.awayTeam.text=m.away_team_name
        binding.homeTeamScore.text=m.home_team_goals
        binding.awayTeamScore.text=m.away_team_goals
        binding.stage.text=m.Stage
    }
    fun stats(teamArrayList:ArrayList<Match>,name:String):Stats{
        var win=0
        var lose =0
        var draw=0
        var cups=0

       for (i in 0 until (teamArrayList.size)-1){
           var team=teamArrayList[i]
           if (name==team.home_team_name){
               if (team.home_team_goals!!>team.away_team_goals!!){
                   win++
               }
               else if (team.home_team_goals!!<team.away_team_goals!!){
                   lose++
               }
               else if (team.home_team_goals==team.away_team_goals) {
                   draw++
               }
           }
           if (name==team.away_team_name){
               if (team.away_team_goals!! > team.home_team_goals!!){
                   win++
               }
               else if (team.away_team_goals!!<team.home_team_goals!!){
                   lose++
               }
               else if (team.home_team_goals==team.away_team_goals) {
                   draw++
               }
           }
           if (team.Stage=="Final"){
               if (name==team.home_team_name){
                   if (team.home_team_goals!!>=team.away_team_goals!!){
                       cups++
                   }
               }
              else if (name==team.away_team_name) {
                   if (team.away_team_goals!! > team.home_team_goals!!) {
                       cups++
                   }
               }

           }
       }
        return Stats(teamName = name,
            matchPlayed = teamArrayList.size-1,
            matchWon = win,
            matchDraw = draw,
            matchLost = lose,
            worldCup = cups)
    }
}