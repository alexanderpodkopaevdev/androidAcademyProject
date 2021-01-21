package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

interface MoviesInsertRepository {
    suspend fun insertMovie(movie: Movie)
}