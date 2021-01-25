package com.alexanderpodkopaev.androidacademyproject.data

import androidx.room.*
import com.alexanderpodkopaev.androidacademyproject.data.entity.ActorEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieActor
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieWithActors

@Dao
interface ActorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActor(actor: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActor(movieActor: MovieActor)

    @Transaction
    @Query("SELECT * FROM movies WHERE mId = :id")
    suspend fun getActorsById(id: Int): MovieWithActors
}