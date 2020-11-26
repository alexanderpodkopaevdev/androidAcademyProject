package com.alexanderpodkopaev.androidacademyproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {
    var listener: ClickListenerItemMovie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list,container,false)
        view?.findViewById<View>(R.id.containerMovie)?.apply {
            setOnClickListener { listener?.onClickItemMovie() }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListenerItemMovie) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


}

interface ClickListenerItemMovie {
    fun onClickItemMovie()
}