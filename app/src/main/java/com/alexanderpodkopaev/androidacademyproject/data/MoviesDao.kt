package com.alexanderpodkopaev.androidacademyproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexanderpodkopaev.androidacademyproject.data.entity.GenreEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieGenre
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieWithGenres

@Dao
interface MoviesDao {

    @Insert
    suspend fun insertMovie(movie: MovieEntity) : Long

    @Insert
    suspend fun insertGenre(genre: GenreEntity) : Long

    @Insert
    suspend fun insertMovieGenres(movieGenre: MovieGenre)

    @Query("SELECT * FROM movies")
    suspend fun getAllMoviesWithGenre() : List<MovieWithGenres>
}