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
    fun removeAccents(str: String): String {
        var str = str
        str = str.replace("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ".toRegex(), "a")
        str = str.replace("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ".toRegex(), "e")
        str = str.replace("ì|í|ị|ỉ|ĩ".toRegex(), "i")
        str = str.replace("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ".toRegex(), "o")
        str = str.replace("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ".toRegex(), "u")
        str = str.replace("ỳ|ý|ỵ|ỷ|ỹ".toRegex(), "y")
        str = str.replace("đ".toRegex(), "d")
        str = str.replace("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ".toRegex(), "A")
        str = str.replace("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ".toRegex(), "E")
        str = str.replace("Ì|Í|Ị|Ỉ|Ĩ".toRegex(), "I")
        str = str.replace("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ".toRegex(), "O")
        str = str.replace("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ".toRegex(), "U")
        str = str.replace("Ỳ|Ý|Ỵ|Ỷ|Ỹ".toRegex(), "Y")
        str = str.replace("Đ".toRegex(), "D")
        return str
    }
}