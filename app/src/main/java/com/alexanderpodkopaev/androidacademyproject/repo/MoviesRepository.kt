package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.Movie

interface MoviesRepository {
    suspend fun getMovies() : List<Movie>
}