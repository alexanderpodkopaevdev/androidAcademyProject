package com.alexanderpodkopaev.androidacademyproject.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.R
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
                showNotification(mostPopularMovie)
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

    private fun showNotification(mostPopularMovie: Movie) {
        initialize()
        Log.d("MyWorker", "Start notification")
        val uri =
            "com.alexanderpodkopaev.androidacademyproject://movies/movie/${mostPopularMovie.id}".toUri()
        Log.d("MyWorker uri", uri.toString())
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val pendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_CONTENT,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notify = NotificationCompat.Builder(context, CHANNEL_NEW_MESSAGES)
            .setContentTitle(mostPopularMovie.title)
            .setContentText(mostPopularMovie.overview)
            .setSmallIcon(R.drawable.star_icon_disable)
            .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        notificationManagerCompat.notify(MOVIE_TAG, mostPopularMovie.id, notify)
    }

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    fun initialize() {
        if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MESSAGES) == null) {

            val channel = NotificationChannelCompat.Builder(
                CHANNEL_NEW_MESSAGES,
                NotificationManagerCompat.IMPORTANCE_HIGH
            )
                .setName("New movie")
                .setDescription("New movie")
                .build()
            notificationManagerCompat.createNotificationChannel(channel)


        }
    }

    companion object {
        private const val REQUEST_CONTENT = 1
        private const val CHANNEL_NEW_MESSAGES = "new_movie"
        private const val MOVIE_TAG = "movie"

    }

}