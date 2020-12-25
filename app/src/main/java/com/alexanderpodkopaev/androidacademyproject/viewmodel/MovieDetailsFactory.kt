package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository


class MovieDetailsFactory(private val repository: MoviesRepository, private val id: Int?) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repository, id)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

