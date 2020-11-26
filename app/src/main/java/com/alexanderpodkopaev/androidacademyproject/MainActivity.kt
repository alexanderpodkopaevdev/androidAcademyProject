package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ClickListenerItemMovie, ClickListenerCurrentMovie {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flFragment, FragmentMoviesList())
                .commit()
        } else {
            supportFragmentManager.findFragmentByTag(TAG_DETAILS) as? FragmentMoviesDetails
        }
    }

    override fun onClickItemMovie() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, FragmentMoviesDetails(),TAG_DETAILS)
            .addToBackStack(null)
            .commit()
    }

    override fun onClickBack() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        private const val TAG_DETAILS = "DETAILS"
    }
}