package com.example.social_network_android.ui.home.profile

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.protected.model.Profile
import com.example.social_network_android.ui.base.BasePresenter

import com.example.social_network_android.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlin.collections.ArrayList

class ProfilePresenter : IProfilePresenter, BasePresenter() {

    override fun getProfile() {
        val requests = ArrayList<Observable<*>>()
        val protectedApi: ProtectedApi = AppApi.create(
            Constants.ApiType.ProtectedApi,
            preferencesHelper!!.getAccessToken()
        ) as ProtectedApi
        requests.add(protectedApi.getUserProfile())
        requests.add(protectedApi.getFollowings())
        (baseView as ProfileFragment)?.showShimmer()
        Observable.zip(
            protectedApi.getUserProfile(),
            protectedApi.getFollowings()
        ) { userProfile, followings ->
            {
                Profile(userProfile, followings)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                (baseView as ProfileFragment)?.hideShimmer()
            }, {
                handleApiError(t = it)
            })

    }
}