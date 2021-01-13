package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.BuildConfig
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.utils.convertToModel

class NetworkMoviesRepo(private val moviesApi: MoviesApi) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> {
        val moviesJsonModel = moviesApi.getMovies(BuildConfig.API_KEY).movies
        return moviesJsonModel.map { movieJsonModel ->
            getMovie(movieJsonModel.id)
        }
    }

    override suspend fun getMovie(id: Int): Movie {
        val movie = moviesApi.getMovie(id, BuildConfig.API_KEY)
        val imageBaseUrl = moviesApi.getConfig(BuildConfig.API_KEY).images.base_url
        return movie.convertToModel(imageBaseUrl)
    }

}