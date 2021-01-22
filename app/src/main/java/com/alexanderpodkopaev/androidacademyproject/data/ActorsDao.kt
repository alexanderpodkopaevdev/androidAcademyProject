package com.alexanderpodkopaev.androidacademyproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexanderpodkopaev.androidacademyproject.data.entity.ActorEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieActor
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieWithActors

@Dao
interface ActorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActor(actor: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActor(movieActor: MovieActor)

    @Query("SELECT * FROM movies WHERE mId = :id")
    suspend fun getActorsById(id: Int): MovieWithActors
}