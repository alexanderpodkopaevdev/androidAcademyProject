package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.RetrofitModule
import com.alexanderpodkopaev.androidacademyproject.data.convertToModel

class NetworkMoviesRepo : MoviesRepository {
    private val apiKey = "cf96a18bb781089d8aa8770ad027a7bc"

    override suspend fun getMovies(): List<Movie> {
        val moviesFromJson = RetrofitModule.moviesApi.getMovies(apiKey).movies
        val genresMap = RetrofitModule.moviesApi.getGenres(apiKey).genres.associateBy { it.id }
        val imageBaseUrl = RetrofitModule.moviesApi.getConfig(apiKey).images.base_url
        return moviesFromJson.map { movieJsonModel ->
            val genres = movieJsonModel.genres.map {
                genresMap[it] ?: throw IllegalArgumentException("Genre not found")
            }
            movieJsonModel.convertToModel(genres, imageBaseUrl)
        }
    }

    override suspend fun getMovie(id: Int?): Movie {
        val movie = RetrofitModule.moviesApi.getMovie(id, apiKey)
        val imageBaseUrl = RetrofitModule.moviesApi.getConfig(apiKey).images.base_url
        return movie.convertToModel(imageBaseUrl)
    }

    override suspend fun getActors(id: Int?) : List<Actor> {
        val imageBaseUrl = RetrofitModule.moviesApi.getConfig(apiKey).images.base_url
        val actorsFromJson = RetrofitModule.moviesApi.getActors(id,apiKey).actors
        return actorsFromJson.map { actorJsonModel ->
            actorJsonModel.convertToModel(imageBaseUrl)
        }
    }

}