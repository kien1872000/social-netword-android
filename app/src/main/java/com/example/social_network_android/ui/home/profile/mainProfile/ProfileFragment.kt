package com.example.social_network_android.ui.home.profile.mainProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.data.api.protected.model.Profile
import com.example.social_network_android.data.api.protected.model.UserInfo
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.home.baseHome.BaseHomeFragment
import com.example.social_network_android.ui.home.imagesDetail.ImagesSliderFragment
import com.example.social_network_android.ui.home.profile.userInfo.UserInfoFragment
import com.example.social_network_android.utils.CommonUtils
import com.example.social_network_android.utils.Constants
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_display_layout.*
import kotlinx.android.synthetic.main.profile_display_layout.view.*


const val USER_ID = "user_id"

class ProfileFragment : BaseHomeFragment(), IProfileView, MediaFilesItemClickListener {
    private lateinit var profilePresenter: ProfilePresenter
    private lateinit var mediaFilesAdapter: MediaFilesAdapter
    private var userId: String? = null
    private var mediaFileSpan = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            userId = it.getString(USER_ID)
        }
        profilePresenter =
            ProfilePresenter(userId).also { it.onAttach(this, PreferencesHelper(requireContext())) }

        super.onCreate(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: String?) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_ID, userId)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        onRefreshLayoutAttach(refresh)
        onShimmerLayoutAttach(shimmer_layout, refresh)
        profilePresenter.getProfile()
    }

    override fun onResume() {
        super.onResume()
        onRefreshing()
        onViewInfoClick()
    }

    private fun setTitle() {
        txt_title.text = profilePresenter?.getUserName()
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.onDetach()
    }

    override fun displayData(data: Profile) {
        Glide.with(this)
            .load(data.userProfileRes.avatar)
            .placeholder(com.example.social_network_android.R.drawable.default_avatar)
            .error(com.example.social_network_android.R.drawable.default_avatar)
            .centerCrop()
            .into(refresh.avatar_img)
        Glide.with(this)
            .load(data.userProfileRes.coverPhoto)
            .centerCrop()
            .into(refresh.cover_photo_img)
        refresh.display_name.text = data.userProfileRes.displayName
        refresh.followers_total.text = data.userProfileRes.followers.toString()
        refresh.followings_total.text = data.userProfileRes.followings.toString()

    }

    private fun onRefreshing() {
        refresh.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                super.onRefresh(refreshLayout)
                profilePresenter.getProfile()
            }
        })
    }

    override fun showOtherPeopleAction() {
        refresh.other_people_action.visibility = View.VISIBLE
    }

    override fun hideOtherPeopleAction() {
        refresh.other_people_action.visibility = View.GONE
    }

    override fun showMediaFilesArea() {
        refresh.image_video_area.visibility = View.VISIBLE
    }

    override fun hideMediaFilesArea() {
        refresh.image_video_area.visibility = View.GONE
    }

    override fun displayMediaFiles(mediaFiles: List<MediaFileRes>) {
        mediaFileSpan = CommonUtils.getScreenWidth() / Constants.MEDIA_ITEM_SIZE
        mediaFilesAdapter = MediaFilesAdapter(mediaFiles.take(mediaFileSpan * 2), this)
        val layoutManager = GridLayoutManager(requireActivity(), mediaFileSpan)
        refresh.media_files.adapter = mediaFilesAdapter
        refresh.media_files.layoutManager = layoutManager
    }

    override fun showImagesDetail(mediaFiles: List<MediaFileRes>, position: Int) {
        val imagesDetailFragment = ImagesSliderFragment.newInstance(position, ArrayList(mediaFiles))
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.home_id, imagesDetailFragment)
            .commit()
    }

    override fun showVideosDetail(mediaFiles: List<MediaFileRes>, position: Int) {
        TODO("Not yet implemented")
    }

    override fun showUserInfoFragment(userInfo: UserInfo) {
        val userInfoFragment = UserInfoFragment.newInstance(userInfo)
        showFragment(userInfoFragment, R.id.home_id)
    }

    override fun onItemClick(position: Int) {
        profilePresenter.onMediaItemClick(position, Constants.MediaFileType.Image)
    }

    private fun onViewInfoClick() {
        view_info.setOnClickListener {
            profilePresenter.onUserInfoClick()
        }
    }


}