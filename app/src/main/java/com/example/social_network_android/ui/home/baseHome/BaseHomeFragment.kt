package com.example.social_network_android.ui.home.baseHome

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.ui.login.main.MainActivity
import com.facebook.shimmer.ShimmerFrameLayout
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.footer.LoadingView
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout

abstract class BaseHomeFragment(): BaseFragment(), IBaseHomeView {
    private var shimmerLayout: ShimmerFrameLayout? = null
    private var displayLayout: ViewGroup? = null
    private var refreshLayout: TwinklingRefreshLayout? = null
    override fun onShimmerLayoutAttach(shimmerLayout: ShimmerFrameLayout, displayLayout: ViewGroup) {
        this.displayLayout = displayLayout
        this.shimmerLayout  = shimmerLayout
    }
    override fun showLoading() {
        shimmerLayout?.let{
            it.visibility = View.VISIBLE
            displayLayout?.visibility = View.GONE
            it.stopShimmer()
            it.startShimmer()
        }
    }

    override fun hideLoading() {
        shimmerLayout?.let {
            it.stopShimmer()
            it.visibility = View.GONE
            displayLayout?.visibility = View.VISIBLE
        }
    }

    override fun onRefreshLayoutAttach(refreshLayout: TwinklingRefreshLayout) {
        this.refreshLayout = refreshLayout
        val headerView = ProgressLayout(requireActivity())
        headerView.setProgressBackgroundColorSchemeResource(R.color.homeBackground)
        headerView.setColorSchemeResources(R.color.line_color)
        val bottomView = LoadingView(requireActivity())
        refreshLayout.setHeaderView(headerView)
        refreshLayout.setBottomView(bottomView)
    }

    override fun finishRefreshing() {
        refreshLayout?.finishRefreshing()
    }

    override fun finishLoadMore() {
        refreshLayout?.finishLoadmore()
    }
    protected fun showFragment(fragment: Fragment, containerId: Int, name: String? =null) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fast_slide_left_enter,
                R.anim.fast_slide_left_exit,
                R.anim.fast_slide_right_exit,
                R.anim.fast_slide_right_enter
            )
            .addToBackStack(name)
            .replace(containerId, fragment)
            .commit()
    }

    override fun onUnAuthorizeError() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        requireActivity().startActivity(intent)
    }
}