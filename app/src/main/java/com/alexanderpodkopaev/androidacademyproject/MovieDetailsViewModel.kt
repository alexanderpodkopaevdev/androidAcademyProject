package com.alexanderpodkopaev.androidacademyproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val app: Application) : AndroidViewModel(app) {

    private var _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    fun fetchMovie(id: Int?) {
        viewModelScope.launch {
            _movie.postValue(findMovie(id))
        }
    }

    private suspend fun findMovie(movieId: Int?): Movie? {
        return loadMovies(getApplication()).find { it.id == movieId }
    }
}