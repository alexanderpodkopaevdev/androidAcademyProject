package com.alexanderpodkopaev.androidacademyproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.adapter.MovieClickListener
import com.alexanderpodkopaev.androidacademyproject.adapter.MoviesAdapter
import com.alexanderpodkopaev.androidacademyproject.di.viewmodel.ViewModelFactory
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import com.alexanderpodkopaev.androidacademyproject.utils.OffsetItemDecoration
import com.alexanderpodkopaev.androidacademyproject.utils.UiUtils.calculateNoOfColumns
import com.alexanderpodkopaev.androidacademyproject.utils.injectViewModel
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MoviesListViewModel
import com.google.android.material.transition.MaterialElevationScale
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FragmentMoviesList : DaggerFragment(), MovieClickListener {

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var srlResfresh: SwipeRefreshLayout
    private lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var moviesRepository: MoviesRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MoviesListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initRecycler(view)
        progressBar = view.findViewById(R.id.pbMovies)
        srlResfresh = view.findViewById(R.id.srlRefresh)

        viewModel = injectViewModel(viewModelFactory)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMovieClick(clMovie: View, movieId: Int) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        val action =
            FragmentMoviesListDirections.actionFragmentMoviesListToFragmentMoviesDetails(movieId)
        val extras = FragmentNavigatorExtras(clMovie to clMovie.transitionName)
        findNavController().navigate(action, extras)
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
