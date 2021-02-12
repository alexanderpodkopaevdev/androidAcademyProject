package com.alexanderpodkopaev.androidacademyproject.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            if (intent != null) {
                handleIntent(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("MyWorker", "Start notification")
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        Log.d("MyWorker", "in handle")
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                Log.d("MyWorker", "in intent")

                val id = intent.data?.lastPathSegment?.toIntOrNull()
                if (id != null) {
                    openMovie(id)
                }
            }
        }
    }

    private fun openMovie(id: Int) {
        Log.d("MyWorker", "open fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, FragmentMoviesDetails.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

}