package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotificationManager

class MoviesUpdateWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val container = MyApp.container
        val moviesFromDB = container.moviesRepository.getMovies()
        val moviesFromNetwork = container.moviesRepository.getMovies(true)
        if (moviesFromDB.isNotEmpty() && moviesFromNetwork.isNotEmpty()) {
            val newMovie = findNewFilms(
                moviesFromDB,
                moviesFromNetwork
            )
            if (newMovie != null) {
                MoviesNotificationManager(context).showNotification(newMovie)
            }
        }
        return if (moviesFromNetwork.isNotEmpty()) {
            Result.success()
        } else {
            Result.failure()
        }
    }

    private fun findNewFilms(
        moviesFromDB: List<Movie>,
        moviesFromNetwork: List<Movie>
    ): Movie? {
        val idsMoviesFromDB = moviesFromDB.map { it.id }
        return moviesFromNetwork.filter { !idsMoviesFromDB.contains(it.id) }
            .maxByOrNull { it.ratings }
    }

}