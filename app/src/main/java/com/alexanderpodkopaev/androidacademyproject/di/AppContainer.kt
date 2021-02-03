package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.data.RetrofitModule
import com.alexanderpodkopaev.androidacademyproject.repo.*

class AppContainer(context: Context) {
    private val database: MoviesDatabase = MoviesDatabase.create(context)
    private var dbDataSource: DBDataSource = DBDataSource(database)
    val moviesRepository: MoviesRepository = MoviesRepoImpl(RetrofitModule.moviesApi, dbDataSource)
    val actorsRepository: ActorsRepository = ActorsRepoImpl(RetrofitModule.moviesApi, dbDataSource)
}