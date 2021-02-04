package com.alexanderpodkopaev.androidacademyproject

import android.app.Application
import com.alexanderpodkopaev.androidacademyproject.di.AppContainer

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
        container.moviesSyncSchedule.schedule()
    }

    companion object {
        lateinit var container: AppContainer
    }
}