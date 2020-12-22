package com.alexanderpodkopaev.androidacademyproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel(var app: Application) : AndroidViewModel(app) {

    private var _moviesList = MutableLiveData<List<Movie>>(emptyList())
    var moviesList: LiveData<List<Movie>> = _moviesList

    fun fetchMovies() {
        viewModelScope.launch {
            val movies = loadMovies(getApplication())
            withContext(Dispatchers.Main) {
                _moviesList.postValue(movies)
            }
        }
    }
}