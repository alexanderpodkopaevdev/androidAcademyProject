package com.alexanderpodkopaev.androidacademyproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexanderpodkopaev.androidacademyproject.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flFragment, FragmentMoviesList())
                .commit()
        }
    }

}