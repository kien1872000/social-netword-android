package com.example.social_network_android.ui.home.signup.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_birthday.*
import kotlinx.android.synthetic.main.fragment_display_name.*
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import java.util.*













private const val DISPLAY_NAME = "displayName"

class BirthdayFragment : BaseFragment() {
    private lateinit var sexFragment: SexFragment
    private var selectedYear = Calendar.getInstance().get(Calendar.YEAR)
    private var selectedMonth = Calendar.getInstance().get(Calendar.MONTH)
    private var selectedDayOfMonth = Calendar.getInstance().get(Calendar.DATE)
    private var birthday = "$selectedYear-$selectedMonth-$selectedDayOfMonth"
    private lateinit var displayName: String
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
        onUserTyping(edt_signup_birthday, txt_birthday_note, next_btn_birthday)
        onNextBtnClick()
        onEdtClick()
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    private fun onNextBtnClick(){
        next_btn_birthday.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    next_btn_birthday_txt.setBackgroundColor(resources.getColor(R.color.login_btn_after_click))
                }
                MotionEvent.ACTION_UP -> {
                    next_btn_birthday_txt.setBackgroundColor(resources.getColor(R.color.login_btn_before_click))
                    validateBirthday()
                }
            }
            true
        }
    }
    private fun onEdtClick(){
        edt_signup_birthday.setOnClickListener {
            showDatePickerDialog()
        }
    }
    private fun showSexFragment() {
        sexFragment = SexFragment.newInstance(displayName, birthday)
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit, R.anim.slide_right_exit, R.anim.slide_right_enter)
            .addToBackStack(null)
            .replace(R.id.main_id, sexFragment)
            .commit()
    }
    private fun showDatePickerDialog() {
        val dateSetListener =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                edt_signup_birthday.setText("ngày $dayOfMonth, tháng ${monthOfYear + 1}, $year")
                selectedYear = year
                birthday = "$year-${monthOfYear+1}-$dayOfMonth"
            }

        val datePickerDialog = DatePickerDialog(requireActivity(),
            dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth
        )
        datePickerDialog.show()
    }
    private fun validateBirthday() {
        if(Calendar.getInstance().get(Calendar.YEAR)-selectedYear<=5) {
            next_btn_birthday.visibility = View.GONE
            txt_birthday_note.visibility = View.VISIBLE
            txt_birthday_note.text = "Ngày sinh không hợp lệ"
        }
        else showSexFragment()
    }
}