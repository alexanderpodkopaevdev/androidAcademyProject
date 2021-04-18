package com.alexanderpodkopaev.androidacademyproject.di.workmanager

import com.alexanderpodkopaev.androidacademyproject.service.ChildWorkerFactory
import com.alexanderpodkopaev.androidacademyproject.service.MoviesUpdateWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(MoviesUpdateWorker::class)
    abstract fun moviesUpdateWorker(factory: MoviesUpdateWorker.Factory): ChildWorkerFactory
}