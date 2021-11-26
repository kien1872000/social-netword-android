package com.example.social_network_android.data.api.protected.model

import com.google.gson.annotations.SerializedName

data class UserProfileReq(val userId: String)
data class UserProfileRes(
    val email: String,
    val displayName: String,
    val birthday: String,
    val avatar: String,
    val coverPhoto: String,
    val address: Address,
    val sex: String,
    val followers: Int,
    val followings: Int,
    val isCurrentUser: Boolean,
    val createdAt: String,
) {
    data class Address(
        val province: AddressProp,
        val district: AddressProp,
        val ward: AddressProp
    ) {
        data class AddressProp(@SerializedName("_id") val id: Int, val name: String)
    }
}

data class FollowingRes(
    val userId: String,
    val displayName: String,
    val avatar: String,
    val followed: Boolean,
    val isCurrentUser: Boolean
)

data class Profile(val userProfileRes: UserProfileRes, val followings: List<FollowingRes>)