package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.adapter.MovieClickListener
import com.alexanderpodkopaev.androidacademyproject.adapter.MoviesAdapter
import com.alexanderpodkopaev.androidacademyproject.data.getMovies

class FragmentMoviesList : Fragment(), MovieClickListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val adapter = MoviesAdapter()
        adapter.bindMovies(generatedMovies)
        adapter.onMovieClickListener = this
        val recyclerViewMovies = view.findViewById<RecyclerView>(R.id.rvMoviesList)
        recyclerViewMovies?.layoutManager =
            GridLayoutManager(context, calculateNoOfColumns(requireContext(), requireContext().resources.getDimension(R.dimen.movie_width)))
        recyclerViewMovies?.adapter = adapter
        recyclerViewMovies?.addItemDecoration(CharacterItemDecoration(8))

        return view
    }


    override fun onMovieClick(movieTitle: String) {
        val movieFragment = FragmentMoviesDetails()
        val bundle = Bundle().apply {
            putString(TITLE, movieTitle)
        }
        movieFragment.arguments = bundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.flFragment, movieFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        const val TITLE = "TITLE"
        var generatedMovies = getMovies().plus(getMovies())
    }

}
