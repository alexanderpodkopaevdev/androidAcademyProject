package com.alexanderpodkopaev.androidacademyproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.adapter.MovieClickListener
import com.alexanderpodkopaev.androidacademyproject.adapter.MoviesAdapter
import com.alexanderpodkopaev.androidacademyproject.utils.OffsetItemDecoration
import com.alexanderpodkopaev.androidacademyproject.utils.UiUtils.calculateNoOfColumns
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MoviesFactory
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MoviesListViewModel

class FragmentMoviesList : Fragment(), MovieClickListener {

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var srlResfresh: SwipeRefreshLayout
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initRecycler(view)
        progressBar = view.findViewById(R.id.pbMovies)
        srlResfresh = view.findViewById(R.id.srlRefresh)
        val appContainer = MyApp.container
        val viewModel = ViewModelProvider(
            this,
            MoviesFactory(appContainer.moviesRepository)
        ).get(MoviesListViewModel::class.java)
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.moviesList.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.bindMovies(movies)
        }
        viewModel.fetchMovies()
        srlResfresh.setOnRefreshListener {
            viewModel.fetchMovies(true)
            srlResfresh.isRefreshing = false
        }

        return view
    }

    override fun onMovieClick(movieId: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.flFragment, FragmentMoviesDetails.newInstance(movieId))
            .addToBackStack(null)
            .commit()
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
