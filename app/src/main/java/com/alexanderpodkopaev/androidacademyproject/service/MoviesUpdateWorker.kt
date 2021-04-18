package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotificationManager
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MoviesUpdateWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    var moviesRepository: MoviesRepository,
    var moviesNotificationManager: MoviesNotificationManager
) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val moviesFromDB = moviesRepository.getMovies()
        val moviesFromNetwork = moviesRepository.getMovies(true)
        if (moviesFromNetwork.isNotEmpty()) {
            val newMovie = findNewFilms(
                moviesFromDB,
                moviesFromNetwork
            )
            if (newMovie != null) {
                moviesNotificationManager.showNotification(newMovie)
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

    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory

}