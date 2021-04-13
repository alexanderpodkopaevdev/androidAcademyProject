package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Schedule {

    @Singleton
    @Provides
    fun provideWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

}