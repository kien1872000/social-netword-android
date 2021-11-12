package com.example.social_network_android.utils

import android.widget.EditText
import android.widget.TextView

object CommonUtils {
    fun isValidEmail(email: String): Boolean{
       val pattern = Regex("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com\$")
        return pattern.matches(email)
    }
    fun isValidPassword(password: String): Boolean {
        val pattern = Regex("^(?=.*[0-9])(?=.*[._!@#\$%^&*])(?=.*[a-zA-Z])[a-zA-Z0-9._!@#\$%^&*]{6,20}\$")
        return pattern.matches(password)
    }
    fun setText(
        edt: EditText?,
        txtTitle: TextView?,
        txtNote: TextView?,
        txtAction: TextView?,
        title: String,
        hint: String,
        note: String,
        action: String
    ) {
        edt?.hint = hint
        txtTitle?.text = title
        txtNote?.text = note
        txtAction?.text = action
    }
}