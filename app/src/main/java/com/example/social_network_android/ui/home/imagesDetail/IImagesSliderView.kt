package com.example.social_network_android.ui.home.imagesDetail

import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.ui.base.IBaseView

interface IImagesSliderView: IBaseView {
    fun showLoadMoreImages(mediaFiles: List<MediaFileRes>)
}