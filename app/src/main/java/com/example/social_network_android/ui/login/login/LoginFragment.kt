package com.example.social_network_android.ui.login.login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.ui.home.newsFeed.NewsFeedActivity
import com.example.social_network_android.ui.login.signup.fragments.DisplayNameFragment
import com.example.social_network_android.utils.CommonUtils
import kotlinx.android.synthetic.main.edt_input.*
import kotlinx.android.synthetic.main.fragment_login.*

private const val EMAIL = "email"
private const val PASSWORD = "password"

class LoginFragment : BaseFragment(), ILoginView {
    private var email: String? = null
    private var password: String? = null
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var displayNameFragment: DisplayNameFragment
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var emailNote: TextView
    private lateinit var passwordNote: TextView
    private lateinit var loginBtn: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenter = LoginPresenter()
        arguments?.let {
            email = it.getString(EMAIL)
            password = it.getString(PASSWORD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginPresenter.onAttach(this, requireContext())
        emailEdt = edt_email
        passwordEdt = edt_password
        emailNote = txt_view_email_error
        passwordNote = txt_view_password_error
        loginBtn = btn_login_text
        onEmailAndPasswordType()
        onLoginBtnClick()
    }

    override fun onResume() {
        super.onResume()
        onSignupBtnClick()
    }

    private fun onLoginBtnClick() {
        loginBtn.setOnClickListener {
            email = if (emailEdt.text.toString().trim()
                    .isNullOrEmpty()
            ) null else emailEdt.text.toString().trim()
            password = if (passwordEdt.text.toString().trim()
                    .isNullOrEmpty()
            ) null else passwordEdt.text.toString().trim()
            Log.d("heelll11", "$email - $password")
            email?.let { email ->
                password?.let { password ->
                    if (!CommonUtils.isValidPassword(password)) passwordNote.visibility =
                        View.VISIBLE
                    if (!CommonUtils.isValidEmail(email)) emailNote.visibility = View.VISIBLE
                    else {
                        loginPresenter.doLogin(email, password)
                    }
                }
            }
        }
    }

    private fun showDisplayNameFragment() {
        displayNameFragment = DisplayNameFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_left_enter,
                R.anim.slide_left_exit,
                R.anim.slide_right_exit,
                R.anim.slide_right_enter
            )
            .replace(R.id.main_id, displayNameFragment)
            .addToBackStack("displayNameFm")
            .commit()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun onSignupBtnClick() {
        btn_signup.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btn_signup_text.background.setTint(resources.getColor(R.color.buttonClickEffect))
                }
                MotionEvent.ACTION_UP -> {
                    showDisplayNameFragment()
                    btn_signup_text.background.setTint(resources.getColor(R.color.signup_btn))
                }
            }
            true
        }
    }

    private fun showNewsFeed() {
        val intent = Intent(requireActivity(), NewsFeedActivity::class.java)
        startActivity(intent)
    }

    override fun onSuccess() {
        Toast.makeText(activity, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
        showNewsFeed()
    }

    private fun onEmailAndPasswordType() {
        onUserTyping(emailEdt, passwordEdt)
        onUserTyping(passwordEdt, emailEdt)
    }

    private fun onUserTyping(edt1: EditText?, edt2: EditText?) {
        edt1?.addTextChangedListener(object : TextWatcher {
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
                val text2 =
                    if (edt2?.text.toString().trim().isEmpty()) null else emailEdt.text.toString()
                        .trim()
                emailNote.visibility = View.INVISIBLE
                passwordNote.visibility = View.INVISIBLE
                val content = s.toString().trim()
                if (content.isNullOrEmpty() || text2.isNullOrEmpty()) {
                    loginBtn.isEnabled = false
                    loginBtn.setTextColor(resources.getColor(R.color.login_txt_before_type))
                } else {
                    loginBtn.isEnabled = true
                    loginBtn.setTextColor(Color.WHITE)
                }
            }
        })
    }

    override fun onLogout() {
        Toast.makeText(activity, "Đăng nhập Thất bại", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.onDetach()
    }
    companion object {
        @JvmStatic
        fun newInstance(email: String, password: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(EMAIL, email)
                    putString(PASSWORD, password)
                }
            }
    }
}