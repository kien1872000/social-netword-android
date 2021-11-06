package com.example.social_network_android.ui.home.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseActivity
import com.example.social_network_android.ui.home.login.ILoginView
import com.example.social_network_android.ui.home.login.LoginFragment
import com.example.social_network_android.ui.home.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var loginFragment: LoginFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLoginFragment()
    }
    private fun showLoginFragment(){
        loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_id, loginFragment)
            .commit()
    }

//    override fun onBackPressed() {
//        val count = supportFragmentManager.backStackEntryCount
//        if(count==0) {
//            super.onBackPressed()
//        }
//        else {
//            supportFragmentManager.popBackStack()
//        }
//    }

}