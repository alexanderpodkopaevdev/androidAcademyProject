package com.alexanderpodkopaev.androidacademyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ActorsAdapter : RecyclerView.Adapter<ActorsViewHolder>() {
    private val actorsList: MutableList<Actor> = mutableListOf()
    private val maxActorsOnScreen = 4

    fun bindActors(actors: List<Actor>) {
        actorsList.clear()
        actorsList.addAll(actors)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        view.layoutParams.width =
            ((parent.measuredWidth - (maxActorsOnScreen) * parent.context.resources.getDimension(R.dimen.small)) / maxActorsOnScreen).toInt()
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    override fun getItemCount() = actorsList.size
}

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivActorPhoto = itemView.findViewById<ImageView>(R.id.ivActorPhoto)
    val tvActorName = itemView.findViewById<TextView>(R.id.tvActorName)

    fun bind(actor: Actor) {
        Glide.with(itemView.context).load(actor.picture).transform(
            CenterCrop(), RoundedCorners(
                itemView.context.resources.getDimension(
                    R.dimen.small
                ).toInt()
            )
        ).into(ivActorPhoto)
        tvActorName.text = actor.name
    }
}
