package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.androidacademyproject.adapter.ActorsAdapter
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.utils.RightOffsetItemDecoration
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        initView(view)
        initRecycler()
        val movieDetailsViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(activity!!.application)).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.fetchMovie(arguments?.getInt(ID))
        movieDetailsViewModel.movie.observe(viewLifecycleOwner) { movie ->
            actorsAdapter.bindActors(movie.actors)
            bindMovie(movie)
        }
        return view
    }

    private fun initView(view: View) {
        val tvBack = view.findViewById<TextView>(R.id.tvBack)
        tvBack.setOnClickListener { fragmentManager?.popBackStack() }
        val ivBack = view.findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener { fragmentManager?.popBackStack() }
        ivBackground = view.findViewById(R.id.ivBackground)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvAge = view.findViewById(R.id.tvAge)
        tvGenre = view.findViewById(R.id.tvGenre)
        rbStar = view.findViewById(R.id.rbStar)
        tvReview = view.findViewById(R.id.tvReview)
        tvDescription = view.findViewById(R.id.tvDescription)
        tvCast = view.findViewById(R.id.tvCast)
        rvActors = view.findViewById(R.id.rvActors)
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

    private fun bindMovie(movie: Movie) {
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

