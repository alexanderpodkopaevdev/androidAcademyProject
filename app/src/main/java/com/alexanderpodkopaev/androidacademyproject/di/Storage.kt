package com.alexanderpodkopaev.androidacademyproject.di

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.repo.DBDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Storage {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): MoviesDatabase = MoviesDatabase.create(context)


}