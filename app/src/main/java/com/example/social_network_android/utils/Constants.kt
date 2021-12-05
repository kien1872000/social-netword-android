package com.example.social_network_android.utils

object Constants {
    const val DISPLAY_NAME_MAX_LENGTH = 50
    const val MEDIA_ITEM_SIZE = 120
    enum class Sex(val value: Int) {
        FEMALE(0),
        MALE(1),
        OTHER(2)
    }
    enum class ApiType{
        PublicApi,
        ProtectedApi
    }
    enum class MediaFileType(val value: String){
        Image("image"),
        Video("video"),
        All("all")
    }
}

