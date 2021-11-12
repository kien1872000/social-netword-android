package com.example.social_network_android.ui.home.signup.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment

abstract class BaseSignupFragment: BaseFragment() {
    protected lateinit var nextBtn: View
    protected lateinit var note: TextView
    protected lateinit var title: TextView
    protected lateinit var action: TextView
    @SuppressLint("ClickableViewAccessibility")
    protected fun onNextBtnClick(perform: () -> Unit) {
        nextBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    action.setBackgroundColor(resources.getColor(R.color.login_btn_after_click))
                }
                MotionEvent.ACTION_UP -> {
                    action.setBackgroundColor(resources.getColor(R.color.login_btn_before_click))
                    perform()
                }
            }
            true
        }
    }
}