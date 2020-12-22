package com.alexanderpodkopaev.androidacademyproject.repo

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies

class MoviesRepoAssetsImpl(val context: Context) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }
}