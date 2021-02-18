package com.alexanderpodkopaev.androidacademyproject.repo

import android.net.Uri
import com.alexanderpodkopaev.androidacademyproject.data.model.MovieToCalendar

interface CalendarRepository {
    fun insertData(startDate: Long, endDate: Long, movie: MovieToCalendar) : Uri?
}