package com.example.social_network_android.ui.home.imagesDetail

import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.ui.base.IBasePresenter

interface IImagesSliderPresenter: IBasePresenter {
    fun loadMoreImages(ownerId: String?)
    fun getDetail(position: Int): MediaFileRes
    fun getMediaListSize(): Int
}