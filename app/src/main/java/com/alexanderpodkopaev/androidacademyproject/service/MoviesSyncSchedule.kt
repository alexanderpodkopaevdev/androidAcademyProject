package com.alexanderpodkopaev.androidacademyproject.service

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MoviesSyncSchedule @Inject constructor(private val context: Context) {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()
    private val constrainedRequest =
        PeriodicWorkRequest.Builder(
            MoviesUpdateWorker::class.java,
            REPEAT_INTERVAL_8,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

    fun schedule() {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            constrainedRequest
        )
    }

    companion object {
        const val WORK_TAG = "WORK_TAG"
        const val REPEAT_INTERVAL_8 = 8L
    }
}