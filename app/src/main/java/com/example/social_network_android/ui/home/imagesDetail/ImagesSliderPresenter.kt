package com.example.social_network_android.ui.home.imagesDetail

import com.example.social_network_android.data.api.AppApi
import com.example.social_network_android.data.api.protected.ProtectedApi
import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.ui.base.BasePresenter
import com.example.social_network_android.utils.Constants
import retrofit2.Call
import retrofit2.Response

class ImagesSliderPresenter(
    private var mediaFiles: List<MediaFileRes>,
) : IImagesSliderPresenter, BasePresenter() {
    private var isLastPage = false
    private var currentPage = 0
    override fun loadMoreImages(ownerId: String?) {
        if(isLastPage) return
        currentPage++
        val protectedApi = AppApi.create(
            Constants.ApiType.ProtectedApi,
            preferencesHelper!!.getAccessToken()
        ) as ProtectedApi
        protectedApi.getMediaFiles(Constants.MediaFileType.Image.value, ownerId, currentPage)
            .enqueue(object : retrofit2.Callback<List<MediaFileRes>> {
                override fun onResponse(
                    call: Call<List<MediaFileRes>>,
                    response: Response<List<MediaFileRes>>,
                ) {
                    if (response.isSuccessful) {
                        isLastPage = response.body()!!.isEmpty()
                        if(response.body()!!.isNotEmpty()) {
                            mediaFiles = mediaFiles + response.body()!!
                            (baseView as IImagesSliderView).showLoadMoreImages(response.body()!!)
                        }

                    } else {
                        handleApiError(response.code(), response.errorBody()!!.string())
                    }
                }

                override fun onFailure(call: Call<List<MediaFileRes>>, t: Throwable) {
                    handleApiError(t = t)
                }

            })
    }

    override fun getDetail(position: Int): MediaFileRes = mediaFiles[position]

    override fun getMediaListSize(): Int = mediaFiles.size
}