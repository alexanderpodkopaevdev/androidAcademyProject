package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovie(id: Int): Movie
}