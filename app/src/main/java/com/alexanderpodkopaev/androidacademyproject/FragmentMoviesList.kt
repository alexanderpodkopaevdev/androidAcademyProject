package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.adapter.MoviesAdapter
import com.alexanderpodkopaev.androidacademyproject.data.getMovies

class FragmentMoviesList : Fragment() {
    private var adapter : MoviesAdapter? = null
    private var recyclerViewMovies: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view?.findViewById<View>(R.id.containerMovie)?.apply {
            setOnClickListener { onClickItemMovie() }
        }
        adapter = MoviesAdapter(getMovies().plus(getMovies()))
        recyclerViewMovies = view.findViewById(R.id.rvMoviesList)
        recyclerViewMovies?.layoutManager = GridLayoutManager(context,2)
        recyclerViewMovies?.adapter = adapter

        return view
    }

    private fun onClickItemMovie() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.flFragment, FragmentMoviesDetails())
            ?.addToBackStack(null)
            ?.commit()
    }

}
