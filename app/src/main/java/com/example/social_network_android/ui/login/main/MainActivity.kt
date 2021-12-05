package com.example.social_network_android.ui.login.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BaseActivity
import com.example.social_network_android.ui.home.newsFeed.NewsFeedActivity
import com.example.social_network_android.ui.login.login.LoginFragment
import com.example.social_network_android.utils.CommonUtils

class MainActivity : BaseActivity(), IMainView {
   private var mainPresenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter().also {
            it.onAttach(this, PreferencesHelper(this))
            it.loadView()
        }
    }

    override fun showLogin() {

        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_id, loginFragment)
            .commit()
    }
    override fun showNewsFeed() {
        val intent = Intent(this, NewsFeedActivity::class.java)
        startActivity(intent)
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