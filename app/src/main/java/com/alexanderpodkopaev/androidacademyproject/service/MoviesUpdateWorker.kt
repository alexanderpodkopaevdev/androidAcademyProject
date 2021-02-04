package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.MyApp

class MoviesUpdateWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val container = MyApp.container
        Log.d("MyWorker", "Start worker")
        return if (container.moviesRepository.getMovies(true).isNotEmpty()) {
            Result.success()
        } else {
            Result.failure()
        }
    }
}