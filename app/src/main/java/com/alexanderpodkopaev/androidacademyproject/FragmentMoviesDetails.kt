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
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies
import com.alexanderpodkopaev.androidacademyproject.utils.RightOffsetItemDecoration
import com.bumptech.glide.Glide
import kotlinx.coroutines.*

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
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val movie = findMovie(arguments?.getInt(ID))
                if (movie != null) {
                    val actorsAdapter = ActorsAdapter()
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            actorsAdapter.bindActors(movie.actors)
                        }
                    }
                    view.findViewById<RecyclerView>(R.id.rvActors).apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = actorsAdapter
                        addItemDecoration(
                            RightOffsetItemDecoration(
                                context.resources.getDimension(R.dimen.small).toInt()
                            )
                        )
                    }
                    Glide.with(requireContext()).load(movie.backdrop)
                        .into(view.findViewById(R.id.ivBackground))
                    view.findViewById<TextView>(R.id.tvTitle).text = movie.title
                    view.findViewById<TextView>(R.id.tvAge).text =
                        getString(R.string.text_age, if (movie.adult) "18" else "13")
                    view.findViewById<TextView>(R.id.tvGanre).text =
                        movie.genres.toString().replace("[", "").replace("]", "")
                    view.findViewById<RatingBar>(R.id.rbStar).progress = movie.ratings.toInt()
                    view.findViewById<TextView>(R.id.tvReview).text =
                        getString(R.string.text_review, movie.voteCount.toString())
                    view.findViewById<TextView>(R.id.tvDescription).text = movie.overview
                    view.findViewById<TextView>(R.id.tvCast).visibility =
                        if (movie.actors.isEmpty()) View.GONE else View.VISIBLE
                }
            }
        }
        return view
    }


    private suspend fun findMovie(movieId: Int?): Movie? {
        var movie: Movie? = null
        GlobalScope.async {
            movie = loadMovies(requireContext()).find { it.id == movieId }
        }.await()
        return movie
    }


    companion object {
        private const val ID = "ID"

        fun newInstance(movieId: Int): FragmentMoviesDetails {
            val movieFragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putInt(ID, movieId)
            movieFragment.arguments = bundle
            return movieFragment
        }
    }


}

