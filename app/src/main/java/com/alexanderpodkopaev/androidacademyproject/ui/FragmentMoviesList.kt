package com.alexanderpodkopaev.androidacademyproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MoviesListViewModel
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.adapter.MovieClickListener
import com.alexanderpodkopaev.androidacademyproject.adapter.MoviesAdapter
import com.alexanderpodkopaev.androidacademyproject.repo.AssetsMoviesRepo
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import com.alexanderpodkopaev.androidacademyproject.utils.OffsetItemDecoration
import com.alexanderpodkopaev.androidacademyproject.utils.UiUtils.calculateNoOfColumns
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MoviesFactory

class FragmentMoviesList : Fragment(), MovieClickListener {

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesRepository: MoviesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initRecycler(view)
        moviesRepository = AssetsMoviesRepo(requireContext())
        val viewModel = ViewModelProvider(this, MoviesFactory(moviesRepository)).get(MoviesListViewModel::class.java)
        viewModel.fetchMovies()
        viewModel.moviesList.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.bindMovies(movies)
        }
        return view
    }

    override fun onMovieClick(movieId: Int) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.flFragment, FragmentMoviesDetails.newInstance(movieId))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun initRecycler(view: View) {
        recyclerViewMovies = view.findViewById(R.id.rvMoviesList)
        moviesAdapter = MoviesAdapter()
        moviesAdapter.onMovieClickListener = this
        recyclerViewMovies.layoutManager =
            GridLayoutManager(
                context,
                calculateNoOfColumns(
                    requireContext(),
                    requireContext().resources.getDimension(R.dimen.movie_width) +
                            requireContext().resources.getDimension(R.dimen.standard)
                )
            )
        recyclerViewMovies.adapter = moviesAdapter
        recyclerViewMovies.addItemDecoration(
            OffsetItemDecoration(
                requireContext().resources.getDimension(
                    R.dimen.standard
                ).toInt()
            )
        )
    }
}
