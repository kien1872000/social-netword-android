package com.example.social_network_android.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.social_network_android.R
import com.example.social_network_android.ui.home.baseHome.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_display_layout.*
import kotlinx.android.synthetic.main.profile_display_layout.view.*


class ProfileFragment : BaseHomeFragment() {
    private lateinit var profilePresenter: ProfilePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        profilePresenter = ProfilePresenter()
        profilePresenter.onAttach(this, requireActivity())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRefreshLayoutAttach(profile_display.refresh)
        onShimmerLayoutAttach(shimmer_layout, profile_display)
        profilePresenter.getProfile()
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.onDetach()
    }

}