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
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies
import com.alexanderpodkopaev.androidacademyproject.utils.OffsetItemDecoration
import com.alexanderpodkopaev.androidacademyproject.utils.UiUtils.calculateNoOfColumns
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment(), MovieClickListener {

    private var coroutineScope: CoroutineScope? = null

    private lateinit var recyclerViewMovies : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        recyclerViewMovies = view.findViewById(R.id.rvMoviesList)

        val adapter = MoviesAdapter()
        adapter.onMovieClickListener = this
        coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope?.launch {
            val movies = loadMovies(requireContext())
            withContext(Dispatchers.Main) {
                adapter.bindMovies(movies)
            }
        }

        recyclerViewMovies.layoutManager =
            GridLayoutManager(
                context,
                calculateNoOfColumns(
                    requireContext(),
                    requireContext().resources.getDimension(R.dimen.movie_width) +
                            requireContext().resources.getDimension(R.dimen.standard)
                )
            )
        recyclerViewMovies.adapter = adapter
        recyclerViewMovies.addItemDecoration(
            OffsetItemDecoration(
                requireContext().resources.getDimension(
                    R.dimen.standard
                ).toInt()
            )
        )

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope?.cancel()
    }

    override fun onMovieClick(movieId: Int) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.flFragment, FragmentMoviesDetails.newInstance(movieId))
            ?.addToBackStack(null)
            ?.commit()
    }

}
