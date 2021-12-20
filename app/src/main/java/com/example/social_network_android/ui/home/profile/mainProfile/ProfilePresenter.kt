package com.example.social_network_android.ui.home.profile.mainProfile

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.protected.model.Profile
import com.example.social_network_android.data.api.protected.model.UserInfo
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.CommonUtils

import com.example.social_network_android.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfilePresenter(private var userId: String?) : IProfilePresenter, BasePresenter() {
    private var profile: Profile? = null
    fun getUserName() = preferencesHelper?.getUserName()
    override fun getProfile() {
        val protectedApi: ProtectedApi = AppApi.create(
            Constants.ApiType.ProtectedApi,
            preferencesHelper!!.getAccessToken()
        ) as ProtectedApi
        baseView!!.showLoading()
        Observable.zip(
            protectedApi.getUserProfile(userId),
            protectedApi.getMediaFiles(userId, 0, Constants.MediaFileType.Image.value)
        ) { userProfile, mediaFiles ->
            {
                Profile(userProfile, mediaFiles)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                profile = it()
                (baseView as IProfileView).finishRefreshing()
                baseView!!.hideLoading()

                loadProfile(profile!!)
            }, {
                baseView!!.hideLoading()
                handleApiError(t = it)
            })
    }

    override fun onMediaItemClick(position: Int, type: Constants.MediaFileType) {
        (baseView as IProfileView).showImagesDetail(profile!!.mediaFileRes, position)
    }

    override fun onUserInfoClick() {
        profile?.userProfileRes?.apply {
            val userInfo = UserInfo(
                displayName,
                birthday,
                sexNumber,
                address,
                isCurrentUser
            )
            (baseView as IProfileView).showUserInfoFragment(userInfo)
        }
    }


    private fun loadProfile(data: Profile) {
        (baseView as IProfileView).apply {
            val userProfileRes = data.userProfileRes
            val mediaFiles = data.mediaFileRes
            if (mediaFiles.isNotEmpty()) showMediaFilesArea()
            else showMediaFilesArea()
            if (userProfileRes.isCurrentUser) hideOtherPeopleAction()
            else showOtherPeopleAction()
            displayData(data)
            displayMediaFiles(data.mediaFileRes)
        }

    }

}