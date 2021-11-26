package com.example.social_network_android.ui.home.baseHome

import android.view.ViewGroup
import com.example.social_network_android.ui.base.IBaseView
import com.facebook.shimmer.ShimmerFrameLayout
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout

interface IBaseHomeView: IBaseView {
    fun onShimmerLayoutAttach(shimmer: ShimmerFrameLayout, displayLayout: ViewGroup)
    fun showShimmer()
    fun hideShimmer()
    fun onRefreshLayoutAttach(refreshLayout: TwinklingRefreshLayout)
    fun finishRefreshing()
    fun finishLoadMore()
}