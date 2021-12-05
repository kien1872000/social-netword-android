package com.example.social_network_android.ui.home.baseHome

import android.content.Intent
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseActivity
import com.example.social_network_android.ui.home.profile.mainProfile.ProfileFragment
import com.example.social_network_android.ui.login.main.MainActivity
import com.facebook.shimmer.ShimmerFrameLayout
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout

abstract class BaseHomeActivity: BaseActivity(), IBaseHomeView {
    override fun onShimmerLayoutAttach(shimmer: ShimmerFrameLayout, displayLayout: ViewGroup) {
        TODO("Not yet implemented")
    }

    override fun showShimmer() {
        TODO("Not yet implemented")
    }

    override fun hideShimmer() {
        TODO("Not yet implemented")
    }

    override fun onRefreshLayoutAttach(refreshLayout: TwinklingRefreshLayout) {
        TODO("Not yet implemented")
    }

    override fun finishLoadMore() {
        TODO("Not yet implemented")
    }

    override fun finishRefreshing() {
        TODO("Not yet implemented")
    }
    protected fun showFragment(fragment: Fragment, containerId: Int, name: String? =null) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fast_slide_left_enter,
                R.anim.fast_slide_left_exit,
                R.anim.fast_slide_right_exit,
                R.anim.fast_slide_right_enter
            )
            .replace(containerId, fragment)
            .commit()
    }

    override fun onUnAuthorizeError() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}