package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotificationManager
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import javax.inject.Inject

class MoviesUpdateWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    @Inject
    lateinit var moviesRepository: MoviesRepository

    override suspend fun doWork(): Result {
        val container = MyApp.container
        val moviesFromDB = moviesRepository.getMovies()
        val moviesFromNetwork = moviesRepository.getMovies(true)
        if (moviesFromNetwork.isNotEmpty()) {
            val newMovie = findNewFilms(
                moviesFromDB,
                moviesFromNetwork
            )
            if (newMovie != null) {
                MyApp.container.moviesNotificationManager.showNotification(newMovie)
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
        val idsMoviesFromDB = moviesFromDB.map { it.id }.toSet()
        return moviesFromNetwork.filter { !idsMoviesFromDB.contains(it.id) }
            .maxByOrNull { it.ratings }
    }

}