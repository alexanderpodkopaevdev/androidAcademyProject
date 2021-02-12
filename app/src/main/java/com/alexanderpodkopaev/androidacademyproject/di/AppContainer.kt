package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import androidx.work.WorkManager
import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.data.RetrofitModule
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotification
import com.alexanderpodkopaev.androidacademyproject.repo.*
import com.alexanderpodkopaev.androidacademyproject.service.MoviesSyncSchedule

class AppContainer(context: Context) {

    private val database: MoviesDatabase = MoviesDatabase.create(context)
    private var dbDataSource: DBDataSource = DBDataSource(database)
    private val workManager: WorkManager = WorkManager.getInstance(context)
    val moviesRepository: MoviesRepository = MoviesRepoImpl(RetrofitModule.moviesApi, dbDataSource)
    val actorsRepository: ActorsRepository = ActorsRepoImpl(RetrofitModule.moviesApi, dbDataSource)
    val moviesSyncSchedule: MoviesSyncSchedule = MoviesSyncSchedule(workManager)
    val moviesNotification: MoviesNotification = MoviesNotification(context)
}