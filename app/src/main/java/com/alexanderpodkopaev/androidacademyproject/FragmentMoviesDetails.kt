package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.adapter.ActorsAdapter
import com.alexanderpodkopaev.androidacademyproject.data.MovieModel

class FragmentMoviesDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<TextView>(R.id.tvBack)
            .setOnClickListener { fragmentManager?.popBackStack() }
        view.findViewById<ImageView>(R.id.ivBack)
            .setOnClickListener { fragmentManager?.popBackStack() }
        val movie = findMovie(arguments?.getString(TITLE))
        if (movie != null) {
            val actorsAdapter = ActorsAdapter()
            actorsAdapter.bindActors(movie.actors)
            view.findViewById<RecyclerView>(R.id.rvActors).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = actorsAdapter
                addItemDecoration(CharacterItemDecoration(16))
            }
            view.findViewById<ImageView>(R.id.ivBackground)
                ?.setImageDrawable(resources.getDrawable(movie.picture, context?.theme))
            view.findViewById<TextView>(R.id.tvTitle).text = movie.title
            view.findViewById<TextView>(R.id.tvAge).text =
                getString(R.string.text_age, movie.age.toString())
            view.findViewById<TextView>(R.id.tvGanre).text = movie.genre
            view.findViewById<RatingBar>(R.id.rbStar).progress = movie.rating
            view.findViewById<TextView>(R.id.tvReview).text =
                getString(R.string.text_review, movie.countReview.toString())
            view.findViewById<TextView>(R.id.tvDescription).text = movie.storyline
        }

        return view
    }


    private fun findMovie(movieTitle: String?): MovieModel? {
        return FragmentMoviesList.generatedMovies.find { it.title == movieTitle }
    }


    companion object {
        private const val TITLE = "TITLE"

        fun newInstance(movieTitle: String): FragmentMoviesDetails {
            val movieFragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putString(TITLE, movieTitle)
            movieFragment.arguments = bundle
            return movieFragment
        }
    }


}

