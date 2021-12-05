package com.example.social_network_android.data.api.protected.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaFileRes(
    val userId: String,
    val displayName: String,
    val des: String,
    val url: String,
    val type: String,
    val groupId: String,
    val createdAt: String,
): Parcelable

