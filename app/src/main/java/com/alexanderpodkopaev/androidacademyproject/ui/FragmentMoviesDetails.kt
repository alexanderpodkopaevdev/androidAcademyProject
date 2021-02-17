package com.alexanderpodkopaev.androidacademyproject.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.adapter.ActorsAdapter
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.utils.RightOffsetItemDecoration
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MovieDetailsFactory
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MovieDetailsViewModel
import com.bumptech.glide.Glide

class FragmentMoviesDetails : Fragment() {

    private lateinit var ivBackground: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvGenre: TextView
    private lateinit var rbStar: RatingBar
    private lateinit var tvReview: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvCast: TextView
    private lateinit var rvActors: RecyclerView
    private lateinit var actorsAdapter: ActorsAdapter
    private lateinit var pbActors: ProgressBar
    private lateinit var btnAddToCalendar: Button
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var isRationaleShown = false
    private lateinit var movie: Movie


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        val movieId = arguments?.getInt(ID)
        if (movieId != null) {
            initView(view)
            initRecycler()
            val appContainer = MyApp.container
            val movieDetailsViewModel = ViewModelProvider(
                this,
                MovieDetailsFactory(
                    appContainer.moviesRepository,
                    appContainer.actorsRepository,
                    movieId
                )
            ).get(
                MovieDetailsViewModel::class.java
            )
            movieDetailsViewModel.movie.observe(viewLifecycleOwner) { movieFromModel ->
                movie = movieFromModel
                bindMovie()
            }
            movieDetailsViewModel.actors.observe(viewLifecycleOwner) { actors ->
                actorsAdapter.bindActors(actors)
            }
            movieDetailsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                pbActors.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
            movieDetailsViewModel.fetchMovie()
            btnAddToCalendar.setOnClickListener { addMovieToCalendar() }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) onCalendarPermissionGranted() else onCalendarPermissionNotGranted()
            }
    }

    private fun onCalendarPermissionNotGranted() {
        Toast.makeText(
            requireContext(),
            getString(R.string.calendar_permission_not_granted),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onCalendarPermissionGranted() {
        sendMovieInfoToCalendar()
    }

    private fun sendMovieInfoToCalendar() {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.flFragment,
                FragmentCalendar.newInstance(movie.id, movie.title, movie.overview, movie.runtime)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun addMovieToCalendar() {
        activity?.let {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.WRITE_CALENDAR
                ) == PackageManager.PERMISSION_GRANTED -> onCalendarPermissionGranted()
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_CALENDAR) -> showCalendarPermissionExplanationDialog()
                isRationaleShown -> showCalendarPermissionDeniedDialog()
                else -> requestCalendarPermission()
            }
        }
    }

    private fun checkCalendarPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_CALENDAR
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun showCalendarPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.open_settings))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + requireContext().packageName)
                    )
                )
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showCalendarPermissionExplanationDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.acces_to_calendar))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                isRationaleShown = true
                requestCalendarPermission()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }

    private fun requestCalendarPermission() {
        requestPermissionLauncher.launch(android.Manifest.permission.WRITE_CALENDAR)
    }


    private fun initView(view: View) {
        val tvBack = view.findViewById<TextView>(R.id.tvBack)
        tvBack.setOnClickListener { parentFragmentManager.popBackStack() }
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener { parentFragmentManager.popBackStack() }
        ivBackground = view.findViewById(R.id.ivBackground)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvAge = view.findViewById(R.id.tvAge)
        tvGenre = view.findViewById(R.id.tvGenre)
        rbStar = view.findViewById(R.id.rbStar)
        tvReview = view.findViewById(R.id.tvReview)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvCast = view.findViewById(R.id.tvCast)
        rvActors = view.findViewById(R.id.rvActors)
        pbActors = view.findViewById(R.id.pbActors)
        btnAddToCalendar = view.findViewById(R.id.btnAddToCalendar)
    }

    private fun initRecycler() {
        actorsAdapter = ActorsAdapter()
        rvActors.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvActors.adapter = actorsAdapter
        rvActors.addItemDecoration(
            RightOffsetItemDecoration(
                requireContext().resources.getDimension(R.dimen.small).toInt()
            )
        )
    }

    private fun bindMovie() {
        Glide.with(requireContext()).load(movie.backdrop).into(ivBackground)
        tvTitle.text = movie.title
        tvAge.text = getString(
            R.string.text_age,
            if (movie.adult) getString(R.string.text_age_adult) else getString(R.string.text_age_child)
        )
        tvGenre.text =
            movie.genres.toString().replace("[", "").replace("]", "")
        rbStar.progress = movie.ratings.toInt()
        tvReview.text = getString(R.string.text_review, movie.voteCount.toString())
        tvDescription.text = movie.overview
        tvCast.visibility = if (movie.actors.isEmpty()) View.GONE else View.VISIBLE
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

