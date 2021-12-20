package com.example.social_network_android.data.api.protected.model

data class PostRes(val postId: String,
val userId: String,
val groupId: String?,
val groupName: String?,
val groupBackgroundImage: String?,
val userDisplayName: String?,
val userAvatar: String?,
val files: List<File>?,
val description: String,
val createdAt: String)
data class File(val url: String, val type: String)