package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.RetrofitModule
import com.alexanderpodkopaev.androidacademyproject.data.convertToModel

class NetworkMoviesRepo: MoviesRepository {
    private val apiKey = "cf96a18bb781089d8aa8770ad027a7bc"

    override suspend fun getMovies(): List<Movie> {
        val moviesFromJson = RetrofitModule.moviesApi.getMovies(apiKey).results
        val genresMap = RetrofitModule.moviesApi.getGenres(apiKey).genres.associateBy { it.id }
        return moviesFromJson.map { movieJsonModel ->
            val genres = movieJsonModel.genres.map { genresMap[it]  ?: throw IllegalArgumentException("Genre not found") }
            movieJsonModel.convertToModel(genres)
        }
    }
}