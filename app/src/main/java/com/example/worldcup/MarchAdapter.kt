package com.example.worldcup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcup.databinding.ItemMatchBinding

class MarchAdapter(private var matchesList:ArrayList<Match>,var listener:onClickListenerInteractions):RecyclerView.Adapter<MarchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match,parent,false)
    )

    override fun getItemCount()= matchesList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        var currentMatch=matchesList[position]
        holder.binding.apply {
            tvStage.text=currentMatch.Stage
            tvTeam1.text=currentMatch.home_team_name
            tvTeam2.text=currentMatch.away_team_name
            tvTeam1Score.text=currentMatch.home_team_goals
            tvTeam2Score.text=currentMatch.away_team_goals
            tvYear.text=currentMatch.year
            if (currentMatch.home_team_goals!! >currentMatch.away_team_goals!!){
                tvTeam1Score.setTextColor(ContextCompat.getColor(holder.binding.root.context,R.color.Green))
                tvTeam2Score.setTextColor(ContextCompat.getColor(holder.binding.root.context,R.color.Red))
            }
            else if (currentMatch.home_team_goals!! <currentMatch.away_team_goals!!){
                tvTeam1Score.setTextColor(ContextCompat.getColor(holder.binding.root.context,R.color.Red))
            tvTeam2Score.setTextColor(ContextCompat.getColor(holder.binding.root.context,R.color.Green))
                }
            else if (currentMatch.home_team_goals!! ==currentMatch.away_team_goals!!){
                tvTeam1Score.setTextColor(ContextCompat.getColor(holder.binding.root.context,R.color.textColor))
                tvTeam2Score.setTextColor(ContextCompat.getColor(holder.binding.root.context,R.color.textColor))
            }
            tvTeam1.setOnClickListener{listener.onCLickName(currentMatch.home_team_name!!)}
            tvTeam2.setOnClickListener{listener.onCLickName(currentMatch.away_team_name!!)}
        }

    }
    fun setData(list:ArrayList<Match>){
        matchesList=list
        notifyDataSetChanged()

    }

    class MatchViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var binding = ItemMatchBinding.bind(itemView)
    }
}