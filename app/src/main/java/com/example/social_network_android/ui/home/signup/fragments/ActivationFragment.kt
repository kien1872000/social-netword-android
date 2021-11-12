package com.example.social_network_android.ui.home.signup.fragments

import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.ui.home.signup.SignupPresenter
import com.example.social_network_android.ui.home.signup.views.ISignupView
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants
import kotlinx.android.synthetic.main.edt_form.*
import kotlinx.android.synthetic.main.edt_input.view.*
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*


private const val EMAIL = "email"
private const val PASSWORD = "password"

class ActivationFragment : ScreenWithEdtFragment(){
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var signupPresenter: SignupPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupPresenter = SignupPresenter()
        arguments?.let {
            email = it.getString(EMAIL).toString()
            password = it.getString(PASSWORD).toString()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupPresenter.onAttach(this)
        input = edt_input.edt
        nextBtn = next_btn.next
        note = txt_note
        title = txt_title
        action = next_btn.action
        CommonUtils.setText(
            input,
            title,
            note,
            action,
            getString(R.string.activation_title),
            getString(R.string.activation_hint),
            getString(R.string.activation_note),
            getString(R.string.next_action)
        )
        onUserTyping(input, note, nextBtn, getString(R.string.activation_note))

    }
    private fun performActivationClick(){

    }
    companion object {
        @JvmStatic
        fun newInstance(email: String, password: String) =
            ActivationFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL, email)
                    putString(PASSWORD, password)
                }
            }
    }

}