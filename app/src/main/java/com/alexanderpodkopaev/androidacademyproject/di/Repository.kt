package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.repo.*
import dagger.Module
import dagger.Provides

@Module
class Repository {

    @Provides
    fun provideMoviesRepository(
        moviesApi: MoviesApi,
        dbDataSource: DBDataSource
    ): MoviesRepository =
        MoviesRepoImpl(moviesApi, dbDataSource)

    @Provides
    fun provideActorsRepository(
        moviesApi: MoviesApi,
        dbDataSource: DBDataSource
    ): ActorsRepository =
        ActorsRepoImpl(moviesApi, dbDataSource)

    @Provides
    fun provideCalendarRepository(context: Context): CalendarRepository = CalendarRepoImpl(context)
}