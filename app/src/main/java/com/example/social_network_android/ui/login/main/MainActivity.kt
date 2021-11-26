package com.example.social_network_android.ui.login.main

import android.os.Bundle
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseActivity
import com.example.social_network_android.ui.login.login.LoginFragment

class MainActivity : BaseActivity() {
    private lateinit var loginFragment: LoginFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLoginFragment()
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
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