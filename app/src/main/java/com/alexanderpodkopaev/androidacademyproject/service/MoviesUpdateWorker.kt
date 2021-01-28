package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository

class MoviesUpdateWorker(context: Context, workerParameters: WorkerParameters, private val moviesRepository: MoviesRepository) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        return if (moviesRepository.getMovies(true).isNotEmpty()) {
            Result.success()
        } else {
            Result.failure()
        }

    }
}