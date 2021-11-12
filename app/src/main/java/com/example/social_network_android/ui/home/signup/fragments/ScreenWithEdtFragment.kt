package com.example.social_network_android.ui.home.signup.fragments

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.social_network_android.R

abstract class ScreenWithEdtFragment: BaseSignupFragment(){
    protected lateinit var input: EditText
    protected fun onUserTyping(
        edt: EditText?,
        msgTv: TextView?,
        parent: View?,
        note: String,
    ) {
        edt?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val content = s.toString().trim()
                if (content.isNullOrEmpty()) {
                    msgTv?.let {
                        it.visibility = View.VISIBLE
                        it.text = note
                        it.setTextColor(resources.getColor(R.color.line_color))
                    }
                    parent?.visibility = View.GONE
                } else {
                    msgTv?.visibility = View.GONE
                    parent?.visibility = View.VISIBLE
                }
            }
        })
    }
}