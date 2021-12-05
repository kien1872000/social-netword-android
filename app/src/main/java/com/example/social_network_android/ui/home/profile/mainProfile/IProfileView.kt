package com.example.social_network_android.ui.home.profile.mainProfile

import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.data.api.protected.model.Profile
import com.example.social_network_android.data.api.protected.model.UserInfo
import com.example.social_network_android.ui.home.baseHome.IBaseHomeView

interface IProfileView: IBaseHomeView {
    fun displayData(data: Profile)
    fun showOtherPeopleAction()
    fun hideOtherPeopleAction()
    fun showMediaFilesArea()
    fun hideMediaFilesArea()
    fun displayMediaFiles(mediaFiles: List<MediaFileRes>)
    fun showImagesDetail(mediaFiles: List<MediaFileRes>, position: Int)
    fun showVideosDetail(mediaFiles: List<MediaFileRes>, position: Int)
    fun showUserInfoFragment(userInfo: UserInfo)
}