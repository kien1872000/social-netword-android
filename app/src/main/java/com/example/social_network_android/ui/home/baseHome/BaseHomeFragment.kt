package com.example.social_network_android.ui.home.baseHome

import android.view.View
import android.view.ViewGroup
import com.example.social_network_android.R
import com.example.social_network_android.ui.base.BaseFragment
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
    override fun showShimmer() {
        shimmerLayout?.let{
            it.visibility = View.VISIBLE
            displayLayout?.visibility = View.GONE
            it.stopShimmer()
            it.startShimmer()
        }
    }

    override fun hideShimmer() {
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
}