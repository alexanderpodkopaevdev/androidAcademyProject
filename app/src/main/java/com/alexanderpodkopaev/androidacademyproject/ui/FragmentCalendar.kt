package com.alexanderpodkopaev.androidacademyproject.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.alexanderpodkopaev.androidacademyproject.MyApp
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.data.model.MovieToCalendar
import com.alexanderpodkopaev.androidacademyproject.repo.CalendarRepository
import com.alexanderpodkopaev.androidacademyproject.viewmodel.CalendarFactory
import com.alexanderpodkopaev.androidacademyproject.viewmodel.CalendarViewModel
import com.google.android.material.transition.MaterialContainerTransform
import java.util.*
import javax.inject.Inject


class FragmentCalendar : Fragment() {

    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var calendarViewModel: CalendarViewModel
    private val args : FragmentCalendarArgs by navArgs()

    @Inject
    lateinit var calendarRepository: CalendarRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val movie = MovieToCalendar(
            id = args.id,
            title = args.title,
            overview = args.overview,
            runtime = args.runtime
        )
        calendarViewModel =
            ViewModelProvider(this, CalendarFactory(movie, calendarRepository)).get(
                CalendarViewModel::class.java
            )
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
            calendarViewModel.saveToCalendar()
        }
        calendarViewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError)
                Toast.makeText(
                    requireContext(),
                    R.string.check_date_time,
                    Toast.LENGTH_SHORT
                ).show()
        }
        calendarViewModel.date.observe(viewLifecycleOwner) {
            etDate.setText(it)
        }
        calendarViewModel.time.observe(viewLifecycleOwner) {
            etTime.setText(it)
        }
        calendarViewModel.isSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved)
                Toast.makeText(
                    requireContext(),
                    R.string.saved,
                    Toast.LENGTH_SHORT
                ).show()
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvAddToCalendar = view.findViewById<TextView>(R.id.tvAddToCalendar)
        tvAddToCalendar.transitionName = requireContext().resources.getString(
            R.string.transition_name_cal, args.id
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

    private fun showDataPicker() {
        val calendar: Calendar = Calendar.getInstance()
        val picker =
            DatePickerDialog(
                requireContext(),
                { _, checkYear, monthOfYear, dayOfMonth ->
                    calendarViewModel.setDate(checkYear, monthOfYear, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        picker.show()
    }

    private fun showTimePicker() {
        val calendar: Calendar = Calendar.getInstance()
        val picker =
            TimePickerDialog(
                requireContext(), { _, hourOfDay, minute ->
                    calendarViewModel.setTime(hourOfDay, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true
            )
        picker.show()
    }


}