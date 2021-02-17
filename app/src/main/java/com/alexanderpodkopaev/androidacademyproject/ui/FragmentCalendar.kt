package com.alexanderpodkopaev.androidacademyproject.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.lifecycle.ViewModelProvider
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.viewmodel.CalendarViewModel
import java.util.*


class FragmentCalendar : Fragment() {

    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var calendarViewModel: CalendarViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarViewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        etDate = view.findViewById(R.id.etDate)
        etDate.setOnClickListener {
            showDataPicker()
        }
        etTime = view.findViewById(R.id.etTime)
        etTime.setOnClickListener {
            showTimePicker()
        }
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            if (etDate.text.isNotEmpty() && etTime.text.isNotEmpty()) {
                calendarViewModel.saveToCalendar(
                    id = arguments?.getInt(ID) ?: 0,
                    title = arguments?.getString(TITLE) ?: "",
                    overview = arguments?.getString(OVERVIEW) ?: "",
                    runtime = arguments?.getInt(RUNTIME) ?: 0
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.check_date_time,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        calendarViewModel.date.observe(viewLifecycleOwner) {
            etDate.setText(it)
        }
        calendarViewModel.time.observe(viewLifecycleOwner) {
            etTime.setText(it)
        }
        calendarViewModel.values.observe(viewLifecycleOwner) {
            val uri =
                requireContext().contentResolver.insert(CalendarContract.Events.CONTENT_URI, it)
            Log.d("MyWork", "saveToCalendar: $uri")
            Toast.makeText(
                requireContext(), R.string.saved, Toast.LENGTH_SHORT
            ).show()
        }
        return view
    }

    private fun showDataPicker() {
        val cldr: Calendar = Calendar.getInstance()
        val picker =
            DatePickerDialog(
                requireContext(),
                { _, checkYear, monthOfYear, dayOfMonth ->
                    calendarViewModel.setDate(checkYear, monthOfYear, dayOfMonth)
                },
                cldr.get(Calendar.YEAR),
                cldr.get(Calendar.MONTH),
                cldr.get(Calendar.DAY_OF_MONTH)
            )
        picker.show()
    }

    private fun showTimePicker() {
        val cldr: Calendar = Calendar.getInstance()
        val picker =
            TimePickerDialog(
                requireContext(), { _, hourOfDay, minute ->
                    calendarViewModel.setTime(hourOfDay, minute)
                },
                cldr.get(Calendar.HOUR_OF_DAY),
                cldr.get(Calendar.MINUTE), true
            )
        picker.show()
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