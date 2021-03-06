package com.alexanderpodkopaev.androidacademyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    var onMovieClickListener: MovieClickListener? = null
    private var moviesList: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false),
            onMovieClickListener
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount() = moviesList.size

    fun bindMovies(movies: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }
}

interface MovieClickListener {
    fun onMovieClick(clMovie: View, movieId: Int)
}

class MoviesViewHolder(itemView: View, private val onMovieClickListener: MovieClickListener?) :
    RecyclerView.ViewHolder(itemView) {
    val clMovie = itemView.findViewById<ConstraintLayout>(R.id.clMovie)
    val ivMovie = itemView.findViewById<ImageView>(R.id.ivMovie)
    val tvAge = itemView.findViewById<TextView>(R.id.tvAge)
    val ivFavorite = itemView.findViewById<ImageView>(R.id.ivFavorite)
    val tvGenre = itemView.findViewById<TextView>(R.id.tvGenre)
    val rbStar = itemView.findViewById<RatingBar>(R.id.rbStar)
    val tvReview = itemView.findViewById<TextView>(R.id.tvReview)
    val tvLength = itemView.findViewById<TextView>(R.id.tvLength)
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

    fun bind(movie: Movie) {
        Glide.with(itemView.context).load(movie.poster).error(R.drawable.ic_launcher_background)
            .transform(
                CenterCrop(), RoundedCorners(
                    itemView.context.resources.getDimension(
                        R.dimen.small
                    ).toInt()
                )
            ).into(ivMovie)
        clMovie.transitionName =
            clMovie.context.resources.getString(R.string.transition_name, movie.id)
        tvAge.text = itemView.context.getString(
            R.string.text_age,
            if (movie.adult) itemView.context.getString(R.string.text_age_adult) else itemView.context.getString(
                R.string.text_age_child
            )
        )
        tvGenre.text = movie.genres.toString().replace("[", "").replace("]", "")
        rbStar.progress = movie.ratings.toInt()
        tvReview.text =
            itemView.context.getString(R.string.text_review, movie.voteCount.toString())
        tvLength.text =
            itemView.context.getString(R.string.text_length, movie.runtime.toString())
        tvTitle.text = movie.title
        itemView.setOnClickListener {
            onMovieClickListener?.onMovieClick(clMovie, movie.id)
        }
    }
}