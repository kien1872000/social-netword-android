package com.example.social_network_android.ui.home.imagesDetail

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.data.local.prefs.PreferencesHelper
import com.example.social_network_android.ui.base.BaseFragment
import com.example.social_network_android.ui.home.imagesDetail.listeners.OnItemClickListener
import com.example.social_network_android.ui.home.imagesDetail.listeners.OnSnapPositionChangeListener
import com.example.social_network_android.ui.home.imagesDetail.listeners.SnapOnScrollListener
import com.example.social_network_android.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_image_detail.*
import kotlinx.android.synthetic.main.fragment_image_detail.view.*


private const val POSITION = "position"
private const val MEDIA_FILES = "media_files"
private const val OWNER_ID = "owner_id"

class ImagesSliderFragment : BaseFragment(), OnItemClickListener, OnSnapPositionChangeListener,
    IImagesSliderView {
    private var imageSliderPresenter: ImagesSliderPresenter? = null
    private var position: Int = -1
    private var mediaFiles: List<MediaFileRes>? = null
    private var ownerId: String? = null
    private var isOrderNumberShowing = false

    companion object {
        const val shortAnimationDuration = 500

        @JvmStatic
        fun newInstance(
            position: Int,
            mediaFiles: ArrayList<MediaFileRes>,
            ownerId: String? = null,
        ) =
            ImagesSliderFragment().apply {
                arguments = Bundle().apply {
                    putString(OWNER_ID, ownerId)
                    putInt(POSITION, position)
                    putParcelableArrayList(MEDIA_FILES, mediaFiles)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ownerId = it.getString(OWNER_ID)
            position = it.getInt(POSITION)
            mediaFiles = it.getParcelableArrayList(MEDIA_FILES)
        }
        imageSliderPresenter = ImagesSliderPresenter(mediaFiles!!).also {
            it.onAttach(this,
                PreferencesHelper(requireContext()))
        }

    }

    override fun onResume() {
        super.onResume()
        onCloseBtnClick()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_image_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail.visibility = View.GONE
        img_items_list.adapter = MediaItemsAdapter(mediaFiles!!, this)
        img_items_list.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        img_items_list.setHasFixedSize(true)
        img_items_list.scrollToPosition(this.position)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(img_items_list)
        img_items_list.attachSnapHelperWithListener(snapHelper, onSnapPositionChangeListener = this)
    }

    override fun onClick() {
        hideOrShowDetail()
    }

    override fun onSnapPositionChange(position: Int) {
        val mediaListSize = imageSliderPresenter!!.getMediaListSize()
        if(position==mediaListSize-2) imageSliderPresenter!!.loadMoreImages(ownerId)
        imageSliderPresenter?.getDetail(position).also {
            detail.apply {
                order_number.text = "${position+1}/${mediaListSize}"
                display_name.text = it!!.displayName
                created_at.text =
                    CommonUtils.getDiffFromCreatedAtToNow(it.createdAt)
            }
        }

    }
    private fun onCloseBtnClick() {
        detail.close_btn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    private fun hideOrShowDetail() {
        if (isOrderNumberShowing) {
            isOrderNumberShowing = false
            detail.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        detail.visibility = View.GONE
                    }
                })
        } else {
            isOrderNumberShowing = true
            detail.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate()
                    .alpha(1f)
                    .setDuration(shortAnimationDuration.toLong())
                    .setListener(null)
            }
        }
    }

    private fun RecyclerView.attachSnapHelperWithListener(
        snapHelper: SnapHelper,
        behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
        onSnapPositionChangeListener: OnSnapPositionChangeListener,
    ) {
        snapHelper.attachToRecyclerView(this)
        val snapOnScrollListener =
            SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
        addOnScrollListener(snapOnScrollListener)
    }

    override fun showLoadMoreImages(mediaFiles: List<MediaFileRes>) {
        (img_items_list.adapter as MediaItemsAdapter).apply {
            Toast.makeText(requireContext(), itemCount.toString(), Toast.LENGTH_SHORT).show()
            addItems(mediaFiles)
            notifyItemInserted(itemCount)
        }
    }
}