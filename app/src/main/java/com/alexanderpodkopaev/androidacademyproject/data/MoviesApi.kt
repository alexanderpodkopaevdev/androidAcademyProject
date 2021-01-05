package com.alexanderpodkopaev.androidacademyproject.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/now_playing")
    suspend fun getMovies(@Query("api_key") apiKey: String): AllMoviesData

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): AllGenreData

}