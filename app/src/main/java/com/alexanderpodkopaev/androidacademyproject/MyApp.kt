package com.alexanderpodkopaev.androidacademyproject

import androidx.work.Configuration
import com.alexanderpodkopaev.androidacademyproject.di.AppComponent
import com.alexanderpodkopaev.androidacademyproject.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication(), Configuration.Provider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.moviesSyncSchedule().schedule()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(appComponent.workerFactory()).build()
    }
}