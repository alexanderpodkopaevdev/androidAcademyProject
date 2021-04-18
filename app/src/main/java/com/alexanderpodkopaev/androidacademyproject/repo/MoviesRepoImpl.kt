package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.BuildConfig
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.utils.convertToModel
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val moviesApi: MoviesApi, private val dbDataSource: DBDataSource) :
    MoviesRepository {

    override suspend fun getMovies(force: Boolean): List<Movie> {
        val moviesFromDB = dbDataSource.getMovies()
        return if (force || moviesFromDB.isEmpty()) {
            val moviesJsonModel = moviesApi.getMovies(BuildConfig.API_KEY).movies
            val moviesFromNetwork = moviesJsonModel.map { movieJsonModel ->
                getMovie(movieJsonModel.id, true)
            }
            dbDataSource.insertMovies(moviesFromNetwork)
            moviesFromNetwork
        } else {
            return moviesFromDB
        }
    }

    override suspend fun getMovie(id: Int, force: Boolean): Movie {
        val movieFromDB = dbDataSource.getMovie(id)
        return if (force || movieFromDB == null) {
            val movie = moviesApi.getMovie(id, BuildConfig.API_KEY)
            val imageBaseUrl = moviesApi.getConfig(BuildConfig.API_KEY).images.base_url
            movie.convertToModel(imageBaseUrl)
        } else {
            return movieFromDB
        }
    }

}