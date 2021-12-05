package com.example.social_network_android.ui.home.imagesDetail

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.MediaFileRes
import com.example.social_network_android.ui.home.imagesDetail.listeners.OnItemClickListener

class MediaItemsAdapter(
    private var dataSet: List<MediaFileRes>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MediaItemsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.media_item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.media_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.imgView).load(dataSet[position].url).fitCenter().into(holder.imgView)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    fun addItems(mediaFiles: List<MediaFileRes>) {
        dataSet = dataSet + mediaFiles
    }
}