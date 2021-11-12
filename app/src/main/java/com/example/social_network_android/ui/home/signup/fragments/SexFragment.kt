package com.example.social_network_android.ui.home.signup.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.social_network_android.R
import kotlinx.android.synthetic.main.fragment_sex.*
import android.content.res.ColorStateList
import android.os.Build
import android.view.MotionEvent
import android.widget.TextView
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*


private const val DISPLAY_NAME = "displayName"
private const val BIRTHDAY = "birthday"

class SexFragment : BaseSignupFragment() {
    private lateinit var emailFragment: EmailFragment
    private var sex: Int = -1
    private lateinit var displayName: String
    private lateinit var birthday: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            displayName = it.getString(DISPLAY_NAME).toString()
            birthday = it.getString(BIRTHDAY).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextBtn = next_btn_sex.next
        title = txt_title_sex
        action = next_btn_sex.action
        CommonUtils.setText(
            null,
            title,
            null,
            action,
            getString(R.string.sex_title),
            "",
            "",
            getString(R.string.next_action)
        )
        nextBtn.visibility = View.GONE
        onCheckedChange(view)
        onNextBtnClick(::showEmailFragment)
    }
    private fun onCheckedChange(view: View){
        radio_btn_signup_sex.setOnCheckedChangeListener { _, checkedId ->
            nextBtn.visibility = View.VISIBLE
            setRadioBtnColor(radio_male, resources.getColor(R.color.line_color))
            setRadioBtnColor(radio_female, resources.getColor(R.color.line_color))
            setRadioBtnColor(radio_other, resources.getColor(R.color.line_color))

            val radioButton = view.findViewById<RadioButton>(checkedId)
            onRadioButtonClicked(radioButton)
        }
    }
    private fun onRadioButtonClicked(view: RadioButton) {
        val checked = view.isChecked
        when (view.id) {
            R.id.radio_male ->
                if (checked) {
                    setRadioBtnColor(view, resources.getColor(R.color.forgot_password))
                    sex = Constants.Sex.MALE.value
                }
            R.id.radio_female ->
                if (checked) {
                    setRadioBtnColor(view, resources.getColor(R.color.forgot_password))
                    sex = Constants.Sex.FEMALE.value
                }
            R.id.radio_other ->
                if (checked) {
                    setRadioBtnColor(view, resources.getColor(R.color.forgot_password))
                    sex = Constants.Sex.OTHER.value
                }
        }
    }
    private fun showEmailFragment() {
        emailFragment = EmailFragment.newInstance(displayName, birthday, sex)
        showFragment("emailFm", emailFragment)
    }
    companion object {
        @JvmStatic
        fun newInstance(displayName: String, birthday: String) =
            SexFragment().apply {
                arguments = Bundle().apply {
                    putString(DISPLAY_NAME, displayName)
                    putString(BIRTHDAY, birthday)
                }
            }
    }
    private fun setRadioBtnColor(view: RadioButton, resColorId: Int){
        if (Build.VERSION.SDK_INT >= 21) {
            val colorStateList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_enabled)
                ), intArrayOf(
                    resColorId
                )
            )
            view.buttonTintList = colorStateList
        }
    }
}