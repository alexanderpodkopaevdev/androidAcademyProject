package com.alexanderpodkopaev.androidacademyproject.data

import androidx.room.*
import com.alexanderpodkopaev.androidacademyproject.data.entity.*

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenre(genre: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenres(movieGenre: MovieGenre)

    @Transaction
    @Query("SELECT * FROM movies")
    suspend fun getAllMoviesWithGenre(): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM movies WHERE mId = :id")
    suspend fun getMovieById(id: Long): MovieWithGenres?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActor(actor: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActor(movieActor: MovieActor)

    @Transaction
    @Query("SELECT * FROM movies WHERE mId = :id")
    suspend fun getActorsById(id: Int): MovieWithActors
}