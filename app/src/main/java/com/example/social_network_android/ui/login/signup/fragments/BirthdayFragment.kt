package com.example.social_network_android.ui.login.signup.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import com.example.social_network_android.utils.CommonUtils
import kotlinx.android.synthetic.main.edt_form.*
import kotlinx.android.synthetic.main.edt_input.view.*
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*
import java.util.*

private const val DISPLAY_NAME = "displayName"

class BirthdayFragment : ScreenWithEdtFragment() {
    private lateinit var sexFragment: SexFragment
    private lateinit var birthday: String
    private lateinit var displayName: String
    private var selectedYear: Int = 0
    private var selectedMonth: Int = 0
    private var selectedDate: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            displayName = it.getString(DISPLAY_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birthday, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            BirthdayFragment().apply {
                arguments = Bundle().apply {
                    putString(DISPLAY_NAME, param1)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input = edt_input.edt
        nextBtn = next_btn.next
        note = txt_note
        title = txt_title
        action = next_btn.action
        CommonUtils.setText(
            input,
            title,
            note,
            action,
            getString(R.string.birthday_title),
            getString(R.string.birthday_hint),
            getString(R.string.birthday_note),
            getString(R.string.next_action)
        )
        onUserTyping(input, note, nextBtn, "")
        onNextBtnClick(::showSexFragment)
        onEdtClick()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onEdtClick() {
        input.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                }
                MotionEvent.ACTION_UP -> {
                  showDatePickerDialog()

                }
            }
            true
        }
    }

    private fun showSexFragment() {
        if (Calendar.getInstance().get(Calendar.YEAR) - selectedYear <= 5) {
            nextBtn.visibility = View.GONE
            note.visibility = View.VISIBLE
            note.text = getString(R.string.birthday_invalid)
            note.setTextColor(Color.RED)
        } else {
            sexFragment = SexFragment.newInstance(displayName, birthday)
            showFragment("sexFm", sexFragment)
        }
    }

    private fun showDatePickerDialog() {
        if(selectedYear==0) selectedYear = Calendar.getInstance().get(Calendar.YEAR)
        if(selectedMonth==0) selectedMonth = Calendar.getInstance().get(Calendar.MONTH)
        if(selectedDate==0) selectedDate = Calendar.getInstance().get(Calendar.DATE)
        val dateSetListener =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                input.setText("ngày $dayOfMonth, tháng ${monthOfYear + 1}, $year")
                val day = if(dayOfMonth>10) dayOfMonth else "0$dayOfMonth"
                val month = if(monthOfYear+1>10) monthOfYear+1 else "0${monthOfYear+1}"
                birthday = "$year-$month-$day"
                selectedYear = year
                selectedDate = dayOfMonth
                selectedMonth = monthOfYear
            }

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            dateSetListener, selectedYear, selectedMonth, selectedDate
        )
        datePickerDialog.show()
    }

}