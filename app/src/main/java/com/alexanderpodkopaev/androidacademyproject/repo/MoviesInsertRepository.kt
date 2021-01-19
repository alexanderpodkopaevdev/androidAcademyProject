package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.Movie

interface MoviesInsertRepository {
    suspend fun insertMovie(movie: Movie)
}