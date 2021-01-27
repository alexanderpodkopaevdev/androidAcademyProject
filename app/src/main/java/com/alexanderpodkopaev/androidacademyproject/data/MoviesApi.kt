package com.alexanderpodkopaev.androidacademyproject.data

import com.alexanderpodkopaev.androidacademyproject.data.json.AllActorsData
import com.alexanderpodkopaev.androidacademyproject.data.json.AllMoviesData
import com.alexanderpodkopaev.androidacademyproject.data.json.Configure
import com.alexanderpodkopaev.androidacademyproject.data.json.MovieJsonModel
import com.alexanderpodkopaev.androidacademyproject.data.model.AllGenreData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/now_playing")
    suspend fun getMovies(@Query("api_key") apiKey: String): AllMoviesData

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): AllGenreData

    @GET("configuration")
    suspend fun getConfig(@Query("api_key") apiKey: String): Configure

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String

    ): MovieJsonModel

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String
    ): AllActorsData

}