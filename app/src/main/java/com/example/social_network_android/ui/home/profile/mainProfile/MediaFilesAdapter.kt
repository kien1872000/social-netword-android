package com.example.social_network_android.ui.home.profile.mainProfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.social_network_android.R
import com.example.social_network_android.data.api.protected.model.MediaFileRes

class MediaFilesAdapter(
    private val mediaFiles: List<MediaFileRes>,
    private val mediaFilesItemClickListener: MediaFilesItemClickListener,
) : RecyclerView.Adapter<MediaFilesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fileImage: ImageView = view.findViewById(R.id.file_image)
        val videoLength: TextView = view.findViewById(R.id.video_length)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.media_file_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fileImage.setOnClickListener {
            mediaFilesItemClickListener.onItemClick(position)
        }
        Glide.with(holder.fileImage).load(mediaFiles[position].url).centerCrop()
            .into(holder.fileImage)
    }

    private fun hideVideoLength(videoLengthTextView: TextView) {
        videoLengthTextView.visibility = View.GONE
    }

    private fun showVideoLength(videoLengthTextView: TextView) {
        videoLengthTextView.visibility = View.VISIBLE
    }

    override fun getItemCount() = mediaFiles.size
}