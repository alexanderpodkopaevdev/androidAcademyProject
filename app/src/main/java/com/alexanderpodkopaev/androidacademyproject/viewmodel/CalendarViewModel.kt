package com.alexanderpodkopaev.androidacademyproject.viewmodel

import android.content.ContentValues
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexanderpodkopaev.androidacademyproject.data.model.MovieToCalendar
import com.alexanderpodkopaev.androidacademyproject.repo.CalendarRepository
import java.util.*

class CalendarViewModel(val movie: MovieToCalendar, val calendarRepository: CalendarRepository) :
    ViewModel() {

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _values = MutableLiveData<ContentValues>()
    val values: LiveData<ContentValues> = _values

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    private val _isSaved = MutableLiveData(false)
    val isSaved: LiveData<Boolean> = _isSaved

    fun saveToCalendar() {
        _isError.value = false
        _isSaved.value = false
        if (!date.value.isNullOrEmpty() && !time.value.isNullOrEmpty()) {
            val startMillis: Long = Calendar.getInstance().run {
                set(year, month, day, hour, minute)
                timeInMillis
            }
            val endMillis: Long = startMillis + movie.runtime * 60 * 1000
            if (calendarRepository.insertData(startMillis, endMillis, movie)) {
                _isSaved.value = true
            } else {
                _isError.value = true
            }
        } else {
            _isError.value = true
        }
    }

    fun setDate(checkYear: Int, monthOfYear: Int, dayOfMonth: Int) {
        year = checkYear
        month = monthOfYear
        day = dayOfMonth
        _date.value = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + checkYear
    }

    fun setTime(hourOfDay: Int, checkMinute: Int) {
        hour = hourOfDay
        minute = checkMinute
        val minuteStr = if (checkMinute < 10) "0$checkMinute" else checkMinute
        _time.value = "$hourOfDay:$minuteStr"
    }
}