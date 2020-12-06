package com.alexanderpodkopaev.androidacademyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.data.MovieModel

class MoviesAdapter() :
    RecyclerView.Adapter<MoviesViewHolder>() {
    private var moviesList: MutableList<MovieModel> = mutableListOf()
    var onMovieClickListener: MovieClickListener? = null

    fun bindMovies(movies: List<MovieModel>) {
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

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
}

interface MovieClickListener {
    fun onMovieClick(movieTitle: String)
}

class MoviesViewHolder(itemView: View, private val onMovieClickListener: MovieClickListener?) :
    RecyclerView.ViewHolder(itemView) {
    val ivMovie = itemView.findViewById<ImageView>(R.id.ivMovie)
    val tvAge = itemView.findViewById<TextView>(R.id.tvAge)
    val ivFavorite = itemView.findViewById<ImageView>(R.id.ivFavorite)
    val tvGenre = itemView.findViewById<TextView>(R.id.tvGanre)
    val rbStar = itemView.findViewById<RatingBar>(R.id.rbStar)
    val tvReview = itemView.findViewById<TextView>(R.id.tvReview)
    val tvLength = itemView.findViewById<TextView>(R.id.tvLength)
    val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

    fun bind(movie: MovieModel) {
        ivMovie.setImageDrawable(
            itemView.context.resources.getDrawable(
                movie.picture,
                itemView.context.theme
            )
        )
        tvAge.text = itemView.context.getString(R.string.text_age, movie.age.toString())
        tvGenre.text = movie.genre
        rbStar.progress = movie.rating
        tvReview.text =
            itemView.context.getString(R.string.text_review, movie.countReview.toString())
        tvLength.text =
            itemView.context.getString(R.string.text_length, movie.length.toString())
        tvTitle.text = movie.title
        itemView.setOnClickListener {
            onMovieClickListener?.onMovieClick(movie.title)
        }
    }
}