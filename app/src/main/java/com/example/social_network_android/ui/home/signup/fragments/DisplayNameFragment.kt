package com.example.social_network_android.ui.home.signup.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_display_name.*
import kotlinx.android.synthetic.main.fragment_login.*


class DisplayNameFragment : BaseFragment() {
    private lateinit var birthdayFragment: BirthdayFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onUserTyping(edt_signup_display_name, txt_display_name_note, next_btn_display_name)
        onNextBtnClick()
    }
    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    private fun onNextBtnClick(){
        val displayName = edt_signup_display_name.text.toString().trim()
        next_btn_display_name.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    next_btn_display_name_txt.setBackgroundColor(resources.getColor(R.color.login_btn_after_click))
                }
                MotionEvent.ACTION_UP -> {
                    next_btn_display_name_txt.setBackgroundColor(resources.getColor(R.color.login_btn_before_click))
                    showBirthdayFragment()
                }

            }
            true
        }
    }
    private fun showBirthdayFragment() {
        val displayName = edt_signup_display_name.text.toString().trim()
        birthdayFragment = BirthdayFragment.newInstance(displayName)
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit, R.anim.slide_right_exit, R.anim.slide_right_enter)
            .addToBackStack(null)
            .replace(R.id.main_id, birthdayFragment)
            .commit()
    }

}