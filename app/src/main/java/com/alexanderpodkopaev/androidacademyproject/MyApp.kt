package com.alexanderpodkopaev.androidacademyproject

import android.app.Application
import com.alexanderpodkopaev.androidacademyproject.di.AppComponent
import com.alexanderpodkopaev.androidacademyproject.di.AppContainer
import com.alexanderpodkopaev.androidacademyproject.di.DaggerAppComponent

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
        container.moviesSyncSchedule.schedule()
    }

    companion object {
        lateinit var container: AppContainer

    }
}