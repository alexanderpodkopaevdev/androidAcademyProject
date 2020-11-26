package com.alexanderpodkopaev.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {
    var clickListenerCurrentMovie:ClickListenerCurrentMovie? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details,container,false)
        view?.findViewById<TextView>(R.id.tvBack)?.apply { setOnClickListener { clickListenerCurrentMovie?.onClickBack() } }
        view?.findViewById<ImageView>(R.id.ivBack)?.apply { setOnClickListener { clickListenerCurrentMovie?.onClickBack() } }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListenerCurrentMovie) {
            clickListenerCurrentMovie = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListenerCurrentMovie = null
    }
}
interface ClickListenerCurrentMovie {
    fun onClickBack()
}