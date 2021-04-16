package com.alexanderpodkopaev.androidacademyproject

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.alexanderpodkopaev.androidacademyproject.di.AppComponent
import com.alexanderpodkopaev.androidacademyproject.di.DaggerAppComponent
import com.alexanderpodkopaev.androidacademyproject.service.AppWorkerFactory
import javax.inject.Inject

class MyApp : Application() {

    @Inject
    lateinit var workerFactory: WorkerFactory

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.moviesSyncSchedule().schedule()
        //val factory: AppWorkerFactory = appComponent.workerFactory()
        WorkManager.initialize(this,Configuration.Builder().setWorkerFactory(workerFactory).build())
    }
}