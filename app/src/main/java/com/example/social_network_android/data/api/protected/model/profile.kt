package com.example.social_network_android.data.api.protected.model

import android.os.Parcelable
import com.example.social_network_android.utils.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserProfileReq(val userId: String)
data class UserProfileRes(
    val email: String,
    val displayName: String,
    val birthday: String,
    val avatar: String,
    val coverPhoto: String,
    val address: AddressRes,
    val sex: String,
    val followers: Int,
    val followings: Int,
    val isCurrentUser: Boolean,
    val createdAt: String,
    val hello: String,
)

data class FollowingRes(
    val userId: String,
    val displayName: String,
    val avatar: String,
    val followed: Boolean,
    val isCurrentUser: Boolean,
)

data class Profile(val userProfileRes: UserProfileRes, val mediaFileRes: List<MediaFileRes>)

data class UserInfoReq(val birthday: String, val sex: Int, val address: AddressReq)
@Parcelize
data class UserInfo(
    val displayName: String,
    val birthday: String,
    val sex: String,
    val address: AddressRes,
    val isCurrentUser: Boolean,
): Parcelable