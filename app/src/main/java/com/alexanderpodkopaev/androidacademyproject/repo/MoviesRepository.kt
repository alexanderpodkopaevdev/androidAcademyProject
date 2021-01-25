package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

interface MoviesRepository {
    suspend fun getMovies(force: Boolean = false): List<Movie>
    suspend fun getMovie(id: Int, force: Boolean = false): Movie
}