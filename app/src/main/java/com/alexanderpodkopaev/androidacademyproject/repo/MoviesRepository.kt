package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.Movie

interface MoviesRepository {
    suspend fun getMovies() : List<Movie>
    suspend fun getMovie(id: Int?) : Movie
    suspend fun getActors(id: Int?) : List<Actor>
}