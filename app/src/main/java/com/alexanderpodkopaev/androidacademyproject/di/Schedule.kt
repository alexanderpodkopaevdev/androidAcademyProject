package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import androidx.work.WorkManager
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotificationManager
import com.alexanderpodkopaev.androidacademyproject.service.MoviesSyncSchedule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Schedule {

    @Singleton
    @Provides
    fun provideWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

    @Provides
    fun provideMoviesSyncSchedule(workManager: WorkManager): MoviesSyncSchedule =
        MoviesSyncSchedule(workManager)

    @Singleton
    @Provides
    fun provideMoviesNotificationManager(context: Context): MoviesNotificationManager = MoviesNotificationManager(context)
}