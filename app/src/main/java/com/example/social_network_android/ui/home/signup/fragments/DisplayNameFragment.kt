package com.example.social_network_android.ui.home.signup.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import kotlinx.android.synthetic.main.edt_form.*
import kotlinx.android.synthetic.main.edt_input.view.*
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants


class DisplayNameFragment : ScreenWithEdtFragment() {
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
        input = edt_input.edt
        input.filters = arrayOf<InputFilter>(LengthFilter(Constants.DISPLAY_NAME_MAX_LENGTH))
        nextBtn = next_btn.next
        note = txt_note
        title = txt_title
        action = next_btn.action
        CommonUtils.setText(
            input,
            title,
            note,
            action,
            getString(R.string.display_name_title),
            getString(R.string.display_name_hint),
            getString(R.string.display_name_note),
            getString(R.string.next_action)
        )
        onUserTyping(input, note, nextBtn, "")
        onNextBtnClick(::showBirthdayFragment)
    }


    private fun showBirthdayFragment() {
        val displayName = input.text.toString().trim()
        birthdayFragment = BirthdayFragment.newInstance(displayName)
        showFragment("birthdayFm", birthdayFragment)
    }

}