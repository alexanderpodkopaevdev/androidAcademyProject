package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.repo.ActorsRepository
import com.alexanderpodkopaev.androidacademyproject.repo.DBDataSource
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import com.alexanderpodkopaev.androidacademyproject.ui.FragmentMoviesDetails
import com.alexanderpodkopaev.androidacademyproject.ui.FragmentMoviesList
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Storage::class, Network::class,Repository::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun dataBase(): MoviesDatabase
    fun dbDataSource(): DBDataSource
    fun moviesApi(): MoviesApi
    fun moviesRepository(): MoviesRepository
    fun actorsRepository(): ActorsRepository

    fun inject(fragment: FragmentMoviesDetails)
    fun inject(fragment: FragmentMoviesList)
}