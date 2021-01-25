package com.alexanderpodkopaev.androidacademyproject.repo

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.json.loadMovies
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

class AssetsMoviesRepo(val context: Context) : MoviesRepository {

    override suspend fun getMovies(force: Boolean): List<Movie> {
        return loadMovies(context)
    }

    override suspend fun getMovie(id: Int, force: Boolean): Movie {
        return loadMovies(context).find { it.id == id }!!
    }
}