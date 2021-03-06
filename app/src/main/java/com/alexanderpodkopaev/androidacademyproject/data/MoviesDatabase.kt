package com.alexanderpodkopaev.androidacademyproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexanderpodkopaev.androidacademyproject.data.entity.*

@Database(
    entities = [GenreEntity::class, MovieEntity::class, MovieGenre::class, ActorEntity::class, MovieActor::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

    companion object {
        fun create(context: Context): MoviesDatabase =
            Room.databaseBuilder(context, MoviesDatabase::class.java, "my_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}