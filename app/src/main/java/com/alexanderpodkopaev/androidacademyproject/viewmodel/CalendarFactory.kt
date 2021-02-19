package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexanderpodkopaev.androidacademyproject.data.model.MovieToCalendar
import com.alexanderpodkopaev.androidacademyproject.repo.CalendarRepository

class CalendarFactory(private val movie: MovieToCalendar, private val calendarRepository: CalendarRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        CalendarViewModel::class.java -> CalendarViewModel(movie, calendarRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}