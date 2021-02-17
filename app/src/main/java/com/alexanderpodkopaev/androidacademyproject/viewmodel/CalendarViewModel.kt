package com.alexanderpodkopaev.androidacademyproject.viewmodel

import android.content.ContentValues
import android.provider.CalendarContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CalendarViewModel : ViewModel() {

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

    fun saveToCalendar(id: Int, title: String, overview: String, runtime: Int) {
        val startMillis: Long = Calendar.getInstance().run {
            set(year, month, day, hour, minute)
            timeInMillis
        }
        Log.d("MyWork", "startMillis: $startMillis")
        val endMillis: Long = startMillis + runtime * 60 * 1000

        _values.value = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, startMillis)
            put(CalendarContract.Events.DTEND, endMillis)
            put(CalendarContract.Events.TITLE, title)
            put(CalendarContract.Events.DESCRIPTION, overview)
            put(CalendarContract.Events.CALENDAR_ID, id)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
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