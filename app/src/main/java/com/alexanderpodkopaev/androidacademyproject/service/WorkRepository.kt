package com.alexanderpodkopaev.androidacademyproject.service

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class WorkRepository {
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()
    val constrainedRequest =
        PeriodicWorkRequest.Builder(MoviesUpdateWorker::class.java, 8, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

    companion object {
        const val WORK_TAG = "WORK_TAG"
    }
}