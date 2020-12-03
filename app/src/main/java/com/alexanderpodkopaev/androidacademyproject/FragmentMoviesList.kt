package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.adapter.MovieClickListener
import com.alexanderpodkopaev.androidacademyproject.adapter.MoviesAdapter
import com.alexanderpodkopaev.androidacademyproject.data.MovieModel
import com.alexanderpodkopaev.androidacademyproject.data.getMovies

class FragmentMoviesList : Fragment(), MovieClickListener {
    private var adapter: MoviesAdapter? = null
    private var recyclerViewMovies: RecyclerView? = null
    var generatedMovies = getMovies().plus(getMovies())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        adapter = MoviesAdapter()
        adapter?.bindMovies(generatedMovies)
        adapter?.onMovieClickListener = this
        recyclerViewMovies = view.findViewById(R.id.rvMoviesList)
        recyclerViewMovies?.layoutManager = GridLayoutManager(context, 2)
        recyclerViewMovies?.adapter = adapter

        return view
    }


    override fun onMovieClick(movieTitle: String) {
        val movieFragment = FragmentMoviesDetails()
        val bundle = Bundle().apply {
            putString("TITLE",movieTitle)
        }
        movieFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.flFragment, movieFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}
