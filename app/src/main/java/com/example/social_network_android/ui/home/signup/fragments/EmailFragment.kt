package com.example.social_network_android.ui.home.signup.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants
import kotlinx.android.synthetic.main.edt_form.*
import kotlinx.android.synthetic.main.edt_input.view.*
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*

private const val DISPLAY_NAME = "displayName"
private const val BIRTHDAY = "birthday"
private const val SEX = "sex"

class EmailFragment : ScreenWithEdtFragment() {
    // TODO: Rename and change types of parameters
    private lateinit var passwordFragment: PasswordFragment
    private lateinit var displayName: String
    private lateinit var birthday: String
    private var sex: Int = -1
    private lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            displayName = it.getString(DISPLAY_NAME).toString()
            birthday = it.getString(BIRTHDAY).toString()
            sex = it.getInt(SEX)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input = edt_input.edt
        input.filters =
            arrayOf<InputFilter>(InputFilter.LengthFilter(Constants.DISPLAY_NAME_MAX_LENGTH))
        nextBtn = next_btn.next
        note = txt_note
        title = txt_title
        action = next_btn.action
        CommonUtils.setText(
            input,
            title,
            note,
            action,
            getString(R.string.email_title),
            getString(R.string.email_hint),
            getString(R.string.email_note),
            getString(R.string.next_action)
        )
        onUserTyping(input, note, nextBtn, getString(R.string.email_note))
        onNextBtnClick(::showPasswordFragment)
    }
    private fun showPasswordFragment() {
        email = input.text.toString().trim()
        if (!CommonUtils.isValidEmail(email)) {
            nextBtn.visibility = View.GONE
            note.visibility = View.VISIBLE
            note.text = getString(R.string.email_invalid)
            note.setTextColor(Color.RED)
        } else {
            passwordFragment = PasswordFragment.newInstance(displayName, birthday, sex, email)
            showFragment("passwordFm", passwordFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(displayName: String, birthday: String, sex: Int) =
            EmailFragment().apply {
                arguments = Bundle().apply {
                    putString(DISPLAY_NAME, displayName)
                    putString(BIRTHDAY, birthday)
                    putInt(SEX, sex)
                }
            }
    }
}