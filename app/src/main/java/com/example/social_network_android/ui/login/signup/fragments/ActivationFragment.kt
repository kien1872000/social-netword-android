package com.example.social_network_android.ui.login.signup.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.login.signup.SignupPresenter
import com.example.social_network_android.ui.login.signup.views.IActivationView
import com.example.social_network_android.utils.CommonUtils
import kotlinx.android.synthetic.main.edt_form.edt_input
import kotlinx.android.synthetic.main.edt_form.next_btn
import kotlinx.android.synthetic.main.edt_form.txt_note
import kotlinx.android.synthetic.main.edt_form.txt_title
import kotlinx.android.synthetic.main.edt_input.view.*
import kotlinx.android.synthetic.main.fragment_activation.*
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*


private const val EMAIL = "email"
private const val PASSWORD = "password"

class ActivationFragment : ScreenWithEdtFragment(), IActivationView {
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var activationCode: String
    private lateinit var signupPresenter: SignupPresenter
    private lateinit var resend: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupPresenter = SignupPresenter()
        arguments?.let {
            email = it.getString(EMAIL).toString()
            password = it.getString(PASSWORD).toString()
        }
        signupPresenter =
            SignupPresenter().also { it.onAttach(this, PreferencesHelper(requireContext())) }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resend = resendBtn
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
        onNextBtnClick(::performActivationClick)
        onResendBtnClick()

    }

    private fun performActivationClick() {
        activationCode = input.text.toString().trim()
        signupPresenter.doActivate(email, activationCode)
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

    private fun onResendBtnClick() {
        resend.setOnClickListener {
            signupPresenter.sendActivationCode(email)
        }
    }

    override fun onBadRequestError() {
        nextBtn.visibility = View.GONE
        note.visibility = View.VISIBLE
        note.text = "Kích hoạt thất bại"
        note.setTextColor(Color.RED)
    }

    override fun onActivationSuccess() {
        Toast.makeText(requireActivity(), "Kích hoạt thành công", Toast.LENGTH_SHORT).show()
    }

    override fun onResendSuccess() {
        nextBtn.visibility = View.GONE
        note.visibility = View.VISIBLE
        note.text = getString(R.string.send_activation_code_success)
        note.setTextColor(resources.getColor(R.color.line_color))
    }

    override fun onDestroy() {
        super.onDestroy()
        signupPresenter.onDetach()
    }

}