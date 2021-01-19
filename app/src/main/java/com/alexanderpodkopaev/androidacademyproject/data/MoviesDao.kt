package com.alexanderpodkopaev.androidacademyproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexanderpodkopaev.androidacademyproject.data.entity.GenreEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieGenre
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieWithGenres

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenre(genre: GenreEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenres(movieGenre: MovieGenre)

    @Query("SELECT * FROM movies")
    suspend fun getAllMoviesWithGenre() : List<MovieWithGenres>
}