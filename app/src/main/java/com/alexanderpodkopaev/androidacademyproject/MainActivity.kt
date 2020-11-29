package com.alexanderpodkopaev.androidacademyproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ClickListenerItemMovie {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flFragment, FragmentMoviesList())
                .commit()
        }
    }

    override fun onClickItemMovie() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, FragmentMoviesDetails())
            .addToBackStack(null)
            .commit()
    }

}