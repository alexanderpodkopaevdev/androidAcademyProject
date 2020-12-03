package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.adapter.ActorsAdapter
import com.alexanderpodkopaev.androidacademyproject.data.MovieModel

class FragmentMoviesDetails : Fragment() {
    private var actorsAdapter: ActorsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view?.findViewById<TextView>(R.id.tvBack)
            ?.apply { setOnClickListener { fragmentManager?.popBackStack() } }
        view?.findViewById<ImageView>(R.id.ivBack)
            ?.apply { setOnClickListener { fragmentManager?.popBackStack() } }
        val movie = findMovie(arguments?.getString(FragmentMoviesList.TITLE))
        actorsAdapter = ActorsAdapter()
        actorsAdapter?.bindActors(movie.actors)
        view.findViewById<RecyclerView>(R.id.rvActors).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = actorsAdapter
            addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.HORIZONTAL).apply { context.getDrawable(R.drawable.divider)?.let {
                setDrawable(it)
            } })
        }
        view.findViewById<ImageView>(R.id.ivBackground).apply {
            setImageDrawable(resources.getDrawable(movie.picture, context.theme))
        }
        view.findViewById<TextView>(R.id.tvTitle).apply {
            text = movie.title
        }
        view.findViewById<TextView>(R.id.tvAge).apply {
            text = getString(R.string.text_age, movie.age.toString())
        }
        view.findViewById<TextView>(R.id.tvGanre).apply {
            text = movie.genre
        }
        view.findViewById<RatingBar>(R.id.rbStar).apply {
            progress = movie.rating
        }
        view.findViewById<TextView>(R.id.tvReview).apply {
            text = getString(R.string.text_review, movie.countReview.toString())
        }
        view.findViewById<TextView>(R.id.tvDescription).apply {
            text = movie.storyline
        }

        return view
    }


    private fun findMovie(movieTitle: String?): MovieModel {
        FragmentMoviesList.generatedMovies.forEach {
            if (it.title == movieTitle) return it
        }
        throw IllegalArgumentException()
    }

}
