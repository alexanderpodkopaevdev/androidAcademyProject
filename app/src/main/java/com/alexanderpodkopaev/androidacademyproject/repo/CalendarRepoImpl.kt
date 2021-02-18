package com.alexanderpodkopaev.androidacademyproject.repo

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.CalendarContract
import com.alexanderpodkopaev.androidacademyproject.data.model.MovieToCalendar
import java.util.*

class CalendarRepoImpl(val context: Context) : CalendarRepository {
    override fun insertData(startDate: Long, endDate: Long, movie: MovieToCalendar): Uri? {
        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, startDate)
            put(CalendarContract.Events.DTEND, endDate)
            put(CalendarContract.Events.TITLE, movie.title)
            put(CalendarContract.Events.DESCRIPTION, movie.overview)
            put(CalendarContract.Events.CALENDAR_ID, movie.id)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        return context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
    }
}