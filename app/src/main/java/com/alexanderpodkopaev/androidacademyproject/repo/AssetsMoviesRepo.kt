package com.alexanderpodkopaev.androidacademyproject.repo

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.json.loadMovies
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

class AssetsMoviesRepo(val context: Context) : MoviesRepository {

    override suspend fun getMovies(isNeedOnline: Boolean): List<Movie> {
        return loadMovies(context)
    }

    override suspend fun getMovie(id: Int, isNeedOnline: Boolean): Movie {
        return loadMovies(context).find { it.id == id }!!
    }
}