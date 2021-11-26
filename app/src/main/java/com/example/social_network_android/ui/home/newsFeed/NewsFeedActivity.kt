package com.example.social_network_android.ui.home.newsFeed

import android.os.Bundle
import com.example.social_network_android.R
import com.example.social_network_android.ui.home.baseHome.BaseHomeActivity
import com.example.social_network_android.ui.home.profile.ProfileFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_news_feed.*

class NewsFeedActivity : BaseHomeActivity() {
    private lateinit var tabLayout: TabLayout
    private var profileFragment: ProfileFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed)
        tabLayout = tab_layout
        showTabs()
        onTabSelected()
    }
    private fun showTabs(){
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home_icon))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.follow_icon))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.watch_icon))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.group_icon))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notification_icon))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.profile_icon))
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun onTabSelected() {
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                showTabItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
    private fun showTabItem(position: Int) {
        when(position) {
            0 -> Any()
            1 -> Any()
            2 -> Any()
            3 -> Any()
            4 -> Any()
            5 -> showProfileFragment()
        }
    }
    private fun showProfileFragment() {
        profileFragment = ProfileFragment()
        showFragment("profileFm", profileFragment!!)
    }
}