package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

interface MoviesRepository {
    suspend fun getMovies(isNeedOnline: Boolean = false): List<Movie>
    suspend fun getMovie(id: Int, isNeedOnline: Boolean = false): Movie
}