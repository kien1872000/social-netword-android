package com.example.social_network_android.utils

object Constants {
    const val DISPLAY_NAME_MAX_LENGTH = 50
    enum class Sex(val value: Int) {
        MALE(0),
        FEMALE(1),
        OTHER(2)
    }
    enum class ApiType{
        AuthApi
    }
}

