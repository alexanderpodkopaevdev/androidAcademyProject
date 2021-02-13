package com.alexanderpodkopaev.androidacademyproject.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alexanderpodkopaev.androidacademyproject.R
import java.util.*


class FragmentCalendar : Fragment() {

    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var currentDate: Calendar
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var hourOfDay: Int = 0
    private var minute: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        etDate = view.findViewById<EditText>(R.id.etDate)
        etDate.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            day = cldr.get(Calendar.DAY_OF_MONTH)
            month = cldr.get(Calendar.MONTH)
            year = cldr.get(Calendar.YEAR)
            val picker =
                DatePickerDialog(
                    requireContext(),
                    { view, checkYear, monthOfYear, dayOfMonth ->
                        year = checkYear
                        month = monthOfYear
                        day = dayOfMonth
                        etDate.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + checkYear)
                    },
                    year,
                    month,
                    day
                )
            picker.show()
        }
        etTime = view.findViewById<EditText>(R.id.etTime)
        etTime.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            hourOfDay = cldr.get(Calendar.HOUR_OF_DAY)
            minute = cldr.get(Calendar.MINUTE)
            val picker =
                TimePickerDialog(
                    requireContext(), { view, checkHourOfDay, checkMinute ->
                        hourOfDay = checkHourOfDay
                        minute = checkMinute
                        val minuteStr = if (checkMinute < 10) "0$checkMinute" else checkMinute
                        etTime.setText("$hourOfDay:$minuteStr")
                    }, hourOfDay, minute, true
                )
            picker.show()
        }
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            if (etDate.text.isNotEmpty() && etTime.text.isNotEmpty()) {
                saveToCalendar()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.check_date_time),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }

    private fun saveToCalendar() {
        val startMillis: Long = Calendar.getInstance().run {
            set(year, month, day, hourOfDay, minute)
            timeInMillis
        }
        Log.d("MyWork", "startMillis: $startMillis")
        val runtime = arguments?.getInt(RUNTIME) ?: 0
        val endMillis: Long = startMillis + runtime * 60 * 1000

        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, startMillis)
            put(CalendarContract.Events.DTEND, endMillis)
            put(CalendarContract.Events.TITLE, arguments?.getString(TITLE))
            put(CalendarContract.Events.DESCRIPTION, arguments?.getString(OVERVIEW))
            put(CalendarContract.Events.CALENDAR_ID, arguments?.getInt(ID) ?: 0)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        val uri =
            requireContext().contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
        Log.d("MyWork", "saveToCalendar: $uri")
        Toast.makeText(
            requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        private const val ID = "ID"
        private const val TITLE = "TITLE"
        private const val OVERVIEW = "OVERVIEW"
        private const val RUNTIME = "RUNTIME"

        fun newInstance(id: Int, title: String, overview: String, runtime: Int) =
            FragmentCalendar().apply {
                arguments = Bundle().apply {
                    putInt(ID, id)
                    putString(TITLE, title)
                    putString(OVERVIEW, overview)
                    putInt(RUNTIME, runtime)
                }
            }
    }
}