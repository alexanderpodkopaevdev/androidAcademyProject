package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

class MoviesUpdateWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val container = MyApp.container
        Log.d("MyWorker", "Start worker")
        val moviesFromDB = container.moviesRepository.getMovies()
        val moviesFromNetwork = container.moviesRepository.getMovies(true)
        if (moviesFromDB.isNotEmpty() && moviesFromNetwork.isNotEmpty()) findNewMostPopularAndSendNotification(
            moviesFromDB,
            moviesFromNetwork
        )
        return if (moviesFromNetwork.isNotEmpty()) {
            Result.success()
        } else {
            Result.failure()
        }
    }

    private fun findNewMostPopularAndSendNotification(
        moviesFromDB: List<Movie>,
        moviesFromNetwork: List<Movie>
    ) {
        val diff = findNewFilms(moviesFromDB, moviesFromNetwork)
        if (diff.isNotEmpty()) {
            val mostPopularMovie = findMostPopular(diff)
            if (mostPopularMovie != null) {
                MyApp.container.moviesNotification.showNotification(mostPopularMovie)
            }
        }
    }

    private fun findNewFilms(
        moviesFromDB: List<Movie>,
        moviesFromNetwork: List<Movie>
    ): List<Movie> {
        return moviesFromNetwork.toSet().minus(moviesFromDB.toSet()).toList()
    }

    private fun findMostPopular(diff: List<Movie>): Movie? {
        return diff.maxByOrNull { it.ratings }
    }

}