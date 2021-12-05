package com.example.social_network_android.utils


import android.content.res.Resources
import android.text.format.DateUtils
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object CommonUtils {
    private const val SECONDS_OF_MINUTE = DateUtils.MINUTE_IN_MILLIS/1000
    private const val SECONDS_OF_HOUR = DateUtils.HOUR_IN_MILLIS/1000
    private const val SECONDS_OF_DAY = DateUtils.DAY_IN_MILLIS/1000
    private const val SECONDS_OF_WEEK = SECONDS_OF_DAY*7
    private const val SECONDS_OF_MONTH = SECONDS_OF_WEEK*4
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
        action: String,
    ) {
        edt?.hint = hint
        txtTitle?.text = title
        txtNote?.text = note
        txtAction?.text = action
    }
    fun getScreenWidth(): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Resources.getSystem().displayMetrics.widthPixels/density.roundToInt()
    }

    fun getScreenHeight(): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Resources.getSystem().displayMetrics.heightPixels/density.roundToInt()
    }

    fun getDiffFromCreatedAtToNow(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        val outputFormat = SimpleDateFormat("dd, MMM, 'năm' yyyy", Locale("vi", "VN") )
        val diff = (Date().time/1000) - (inputFormat.parse(dateString).time/1000)

        return when  {
            diff < SECONDS_OF_MINUTE -> "${diff/ SECONDS_OF_MINUTE} giây trước"
            diff < SECONDS_OF_HOUR -> "${diff/ SECONDS_OF_MINUTE} phút trước"
            diff < SECONDS_OF_DAY -> "${diff/ SECONDS_OF_HOUR} giờ trước"
            diff < SECONDS_OF_WEEK -> "${diff/ SECONDS_OF_DAY} ngày trước"
            diff < SECONDS_OF_MONTH -> "${diff/ SECONDS_OF_WEEK} tuần trước"
            else -> outputFormat.format(diff)
        }
    }
    fun getDateDisplayForUser(dateString: String): String {
        val outputFormat = SimpleDateFormat("'Ngày' dd, 'tháng' MM, 'năm' yyyy", Locale("vi", "VN"))
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }
}