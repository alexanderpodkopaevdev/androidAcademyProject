package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexanderpodkopaev.androidacademyproject.repo.DatabaseMoviesRepo
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository


class MoviesFactory(val repository: MoviesRepository, val dbRepository: DatabaseMoviesRepo) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(repository, dbRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

