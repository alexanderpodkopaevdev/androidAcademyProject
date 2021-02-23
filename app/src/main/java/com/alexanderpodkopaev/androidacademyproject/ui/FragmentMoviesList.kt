package com.alexanderpodkopaev.androidacademyproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
import com.google.android.material.transition.MaterialElevationScale

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
        val action = FragmentMoviesListDirections.actionFragmentMoviesListToFragmentMoviesDetails(movieId)
        findNavController().navigate(action)
/*        findNavController().navigate(R.id.action_fragmentMoviesList_to_fragmentMoviesDetails)
        parentFragmentManager.beginTransaction()
            .replace(R.id.flFragment, FragmentMoviesDetails.newInstance(movieId))
            .addSharedElement(clMovie, clMovie.transitionName)
            .addToBackStack(null)
            .commit()*/
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
