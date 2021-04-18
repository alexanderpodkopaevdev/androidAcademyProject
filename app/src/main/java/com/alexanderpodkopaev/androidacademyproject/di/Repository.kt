package com.alexanderpodkopaev.androidacademyproject.di

import com.alexanderpodkopaev.androidacademyproject.repo.*
import dagger.Binds
import dagger.Module

@Module
abstract class Repository {

    @Binds
    abstract fun provideMoviesRepository(moviesRepoImpl: MoviesRepoImpl): MoviesRepository

    @Binds
    abstract fun provideActorsRepoImpl(actorsRepoImpl: ActorsRepoImpl): ActorsRepository

    @Binds
    abstract fun provideCalendarRepoImpl(calendarRepoImpl: CalendarRepoImpl): CalendarRepository
}