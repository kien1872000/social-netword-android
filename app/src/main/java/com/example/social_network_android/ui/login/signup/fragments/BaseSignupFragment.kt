package com.example.social_network_android.ui.login.signup.fragments

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.example.social_network_android.ui.base.BaseFragment

abstract class BaseSignupFragment: BaseFragment() {
    protected lateinit var nextBtn: View
    protected lateinit var note: TextView
    protected lateinit var title: TextView
    protected lateinit var action: TextView
    @SuppressLint("ClickableViewAccessibility")
    protected fun onNextBtnClick(perform: () -> Unit) {
        nextBtn.setOnClickListener {
            perform()
        }
    }
}