package com.example.social_network_android.ui.home.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.ui.home.signup.fragments.DisplayNameFragment
import kotlinx.android.synthetic.main.fragment_login.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : BaseFragment(), ILoginView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var displayNameFragment: DisplayNameFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginPresenter = LoginPresenter(this)
        onLoginBtnClick()
    }

    override fun onResume() {
        super.onResume()
        onSignupBtnClick()
    }
    private fun onLoginBtnClick() {
        btn_login.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()
            Log.d("K231111", "$email $password")
            loginPresenter!!.doLogin(email, password)
        }
    }
    private fun showDisplayNameFragment(){
        displayNameFragment= DisplayNameFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_left_exit, R.anim.slide_right_exit, R.anim.slide_right_enter)
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
    override fun onLoginSuccess() {
        Toast.makeText(activity, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
    }

    override fun onLoginError() {
        Toast.makeText(activity, "Đăng nhập Thất bại", Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}