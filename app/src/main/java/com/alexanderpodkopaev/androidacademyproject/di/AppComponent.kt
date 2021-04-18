package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.di.viewmodel.ViewModelModule
import com.alexanderpodkopaev.androidacademyproject.di.workmanager.AppAssistedInjectModule
import com.alexanderpodkopaev.androidacademyproject.di.workmanager.WorkerModule
import com.alexanderpodkopaev.androidacademyproject.notifications.MoviesNotificationManager
import com.alexanderpodkopaev.androidacademyproject.repo.ActorsRepository
import com.alexanderpodkopaev.androidacademyproject.repo.DBDataSource
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import com.alexanderpodkopaev.androidacademyproject.service.AppWorkerFactory
import com.alexanderpodkopaev.androidacademyproject.service.MoviesSyncSchedule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        Storage::class,
        Network::class,
        Repository::class,
        ViewModelModule::class,
        WorkerModule::class,
        AppAssistedInjectModule::class,
        ActivityModule::class]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun dataBase(): MoviesDatabase
    fun dbDataSource(): DBDataSource
    fun moviesApi(): MoviesApi
    fun moviesRepository(): MoviesRepository
    fun actorsRepository(): ActorsRepository
    fun moviesSyncSchedule(): MoviesSyncSchedule
    fun moviesNotificationManager(): MoviesNotificationManager

    fun workerFactory(): AppWorkerFactory

}