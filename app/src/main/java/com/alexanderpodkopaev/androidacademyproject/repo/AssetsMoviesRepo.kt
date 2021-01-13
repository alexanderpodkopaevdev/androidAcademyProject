package com.alexanderpodkopaev.androidacademyproject.repo

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies

class AssetsMoviesRepo(val context: Context) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }

    override suspend fun getMovie(id: Int): Movie {
        return loadMovies(context).find { it.id == id }!!
    }
}