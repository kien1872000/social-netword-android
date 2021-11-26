package com.example.social_network_android.ui.login.signup.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import com.example.social_network_android.ui.login.signup.SignupPresenter
import com.example.social_network_android.ui.login.signup.views.ISignupView
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants
import kotlinx.android.synthetic.main.edt_form.*
import kotlinx.android.synthetic.main.edt_input.view.*
import kotlinx.android.synthetic.main.rounded_corner_btn.view.*


private const val DISPLAY_NAME = "displayName"
private const val BIRTHDAY = "birthday"
private const val SEX = "sex"
private const val EMAIL = "email"

class PasswordFragment : ScreenWithEdtFragment(), ISignupView {
    private lateinit var activationFragment: ActivationFragment
    private lateinit var displayName: String
    private lateinit var birthday: String
    private var sex: Int = -1
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var signupPresenter: SignupPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            displayName = it.getString(DISPLAY_NAME).toString()
            birthday = it.getString(BIRTHDAY).toString()
            sex = it.getInt(SEX)
            email = it.getString(EMAIL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupPresenter = SignupPresenter()
        signupPresenter.onAttach(this)
        input = edt_input.edt
        input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(Constants.DISPLAY_NAME_MAX_LENGTH))
        nextBtn = next_btn.next
        note = txt_note
        title = txt_title
        action = next_btn.action
        CommonUtils.setText(
            input,
            title,
            note,
            action,
            getString(R.string.password_title),
            getString(R.string.password_hint),
            getString(R.string.password_note),
            getString(R.string.next_action)
        )
        onUserTyping(input, note, nextBtn, getString(R.string.password_note))
        onNextBtnClick(::createAccount)
    }

    override fun onSuccess() {
        requireActivity().supportFragmentManager.popBackStack("displayNameFm", 0)
        requireActivity().supportFragmentManager.popBackStack()
        showActivationFragment()
    }
    private fun showActivationFragment() {
        activationFragment  = ActivationFragment.newInstance(email, password)
        showFragment("activationFm", activationFragment)
    }
    override fun onConflictError() {
        showInvalidEmailDialog()
    }
    private fun createAccount() {
        password = input.text.toString().trim()
        if(!CommonUtils.isValidPassword(password)) {
            nextBtn.visibility = View.GONE
            note.visibility = View.VISIBLE
            note.text = getString(R.string.password_invalid)
            note.setTextColor(Color.RED)
        }
        else {
            signupPresenter.doSignup(displayName, birthday, sex, email, "kien.1807")
        }


    }
    companion object {
        @JvmStatic
        fun newInstance(displayName: String, birthday: String, sex: Int, email: String) =
            PasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(DISPLAY_NAME, displayName)
                    putString(BIRTHDAY, birthday)
                    putInt(SEX, sex)
                    putString(EMAIL, email)
                }
            }
    }
    private fun showInvalidEmailDialog(): AlertDialog {
        val invalidEmailDialog = AlertDialog.Builder(requireActivity())
            .setTitle("Đăng kí không thành công")
            .setMessage("Email đã tồn tại. Bạn muốn nhập lại email hay bạn đã có tài khoản rồi?")
            .setPositiveButton(
                "Đăng nhập"
            ) { dialog, which ->
                requireActivity().supportFragmentManager.popBackStack("displayNameFm", 1)
            }
            .setNegativeButton(
                "Quay lại"
            ) { dialog, which ->
                requireActivity().supportFragmentManager.popBackStack()
            }
            .show()
        return invalidEmailDialog;
    }

    override fun onDestroy() {
        super.onDestroy()
        signupPresenter.onDetach()
    }
}