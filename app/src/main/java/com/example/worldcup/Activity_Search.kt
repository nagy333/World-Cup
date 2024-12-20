package com.example.worldcup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worldcup.databinding.ActivitySearchBinding

class Activity_Search : AppCompatActivity() {
    private lateinit var binding:ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var s=intent.getSerializableExtra("stats") as Stats
        binding.name1.text=s.teamName
        binding.matches1.text=s.matchPlayed.toString()
        binding.win1.text=s.matchWon.toString()
        binding.draw1.text=s.matchDraw.toString()
        binding.lose1.text=s.matchLost.toString()
        binding.cups1.text=s.worldCup.toString()
    }
}