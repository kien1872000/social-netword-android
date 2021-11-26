package com.example.social_network_android.ui.home.baseHome

import android.view.ViewGroup
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseActivity
import com.example.social_network_android.ui.home.profile.ProfileFragment
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
    protected fun showFragment(name: String?, fragment: ProfileFragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_left_enter,
                R.anim.slide_left_exit,
                R.anim.slide_right_exit,
                R.anim.slide_right_enter
            )
            .addToBackStack(name)
            .replace(R.id.tab_item, fragment)
            .commit()
    }
}