package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.BuildConfig
import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.RetrofitModule
import com.alexanderpodkopaev.androidacademyproject.data.convertToModel

class NetworkMoviesRepo : MoviesRepository {

    override suspend fun getMovies(): List<Movie> {
        val moviesJsonModel = RetrofitModule.moviesApi.getMovies(BuildConfig.API_KEY).movies

        //val genresMap = RetrofitModule.moviesApi.getGenres(apiKey).genres.associateBy { it.id }
        //val imageBaseUrl = RetrofitModule.moviesApi.getConfig(apiKey).images.base_url
        return moviesJsonModel.map { movieJsonModel ->
            getMovie(movieJsonModel.id)
/*            val genres = movieJsonModel.genres.map {
                genresMap[it] ?: throw IllegalArgumentException("Genre not found")
            }
            movieJsonModel.convertToModel(genres, imageBaseUrl)*/
        }
    }

    override suspend fun getMovie(id: Int?): Movie {
        val movie = RetrofitModule.moviesApi.getMovie(id, BuildConfig.API_KEY)
        val imageBaseUrl = RetrofitModule.moviesApi.getConfig(BuildConfig.API_KEY).images.base_url
        return movie.convertToModel(imageBaseUrl)
    }

    override suspend fun getActors(id: Int?) : List<Actor> {
        val imageBaseUrl = RetrofitModule.moviesApi.getConfig(BuildConfig.API_KEY).images.base_url
        val actorsFromJson = RetrofitModule.moviesApi.getActors(id,BuildConfig.API_KEY).actors
        return actorsFromJson.map { actorJsonModel ->
            actorJsonModel.convertToModel(imageBaseUrl)
        }
    }

}