package com.alexanderpodkopaev.androidacademyproject

import android.app.Application
import androidx.work.Configuration
import com.alexanderpodkopaev.androidacademyproject.di.AppComponent
import com.alexanderpodkopaev.androidacademyproject.di.DaggerAppComponent

class MyApp : Application(), Configuration.Provider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.moviesSyncSchedule().schedule()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(appComponent.workerFactory()).build()
    }
}