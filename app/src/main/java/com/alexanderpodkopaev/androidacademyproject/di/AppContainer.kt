package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import androidx.work.WorkManager
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotificationManager
import com.alexanderpodkopaev.androidacademyproject.repo.*
import com.alexanderpodkopaev.androidacademyproject.service.MoviesSyncSchedule

class AppContainer(context: Context) {

    //private val database: MoviesDatabase = MoviesDatabase.create(context)
    //private var dbDataSource: DBDataSource = DBDataSource(database)
    private val workManager: WorkManager = WorkManager.getInstance(context)

    //    val moviesRepository: MoviesRepository = MoviesRepoImpl(
//        (context.applicationContext as MyApp).appComponent.moviesApi(),
//        (context.applicationContext as MyApp).appComponent.dbDataSource()
//    )
//    val actorsRepository: ActorsRepository = ActorsRepoImpl(
//        (context.applicationContext as MyApp).appComponent.moviesApi(),
//        (context.applicationContext as MyApp).appComponent.dbDataSource()
//    )
    val moviesSyncSchedule: MoviesSyncSchedule = MoviesSyncSchedule(workManager)
    val moviesNotificationManager: MoviesNotificationManager = MoviesNotificationManager(context)
    val calendarRepository : CalendarRepository = CalendarRepoImpl(context)
}