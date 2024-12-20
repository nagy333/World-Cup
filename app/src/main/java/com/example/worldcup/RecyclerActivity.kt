package com.example.worldcup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.worldcup.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    lateinit var adapter: MarchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list=intent.getParcelableArrayListExtra<Match>("list")
        list?.reverse()
        adapter= list?.let { MarchAdapter(it,object :onClickListenerInteractions{
            override fun onCLickName(name: String) {
                Toast.makeText(this@RecyclerActivity,name,Toast.LENGTH_LONG).show()
            }


        }) }!!
        binding.recyclerMatch.adapter=adapter



    }
}