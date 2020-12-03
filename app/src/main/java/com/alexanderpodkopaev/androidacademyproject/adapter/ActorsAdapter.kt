package com.alexanderpodkopaev.androidacademyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.data.ActorModel

class ActorsAdapter : RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {
    private val actorsList: MutableList<ActorModel> = mutableListOf()

    inner class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivActorPhoto = itemView.findViewById<ImageView>(R.id.ivActorPhoto)
        val tvActorName = itemView.findViewById<TextView>(R.id.tvActorName)

        fun bind(actor: ActorModel) {
            ivActorPhoto.setImageDrawable(
                itemView.resources.getDrawable(
                    actor.image,
                    itemView.context.theme
                )
            )
            tvActorName.text = actor.name
        }
    }

    fun bindActors(actors: List<ActorModel>) {
        actorsList.clear()
        actorsList.addAll(actors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        view.layoutParams.width = (parent.measuredWidth  / 4.5).toInt()
      //  view.layoutParams.height = (parent.measuredWidth  / 4).toInt()
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    override fun getItemCount() = actorsList.size
}