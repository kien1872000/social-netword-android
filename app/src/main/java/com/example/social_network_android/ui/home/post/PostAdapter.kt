package com.example.social_network_android.ui.home.post

import android.content.Context
import android.graphics.Color
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
import com.example.social_network_android.data.api.protected.model.PostRes
import com.example.social_network_android.utils.CommonUtils
import kotlinx.android.synthetic.main.post_item.view.*
import org.w3c.dom.Text
import android.text.style.ForegroundColorSpan

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import androidx.core.content.ContextCompat
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.text.style.ClickableSpan





class PostAdapter(private val context: Context, private val postList: List<PostRes>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userDisplayName: TextView = view.findViewById<TextView>(R.id.display_name)
        val userAvatar = view.findViewById<ImageView>(R.id.user_avatar)
        val createdAt = view.findViewById<TextView>(R.id.created_at)
        val postImage = view.findViewById<ImageView>(R.id.post_image)
        val description = view.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.userAvatar.context).load(postList[position].userAvatar)
            .placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar)
            .into(holder.userAvatar)
        holder.userDisplayName.text = postList[position].userDisplayName
        holder.createdAt.text = CommonUtils.getDiffFromCreatedAtToNow(postList[position].createdAt)
//        holder.description.text = postList[position].description
        val hashtagintitle = SpannableString(postList[position].description)
        val matcher: Matcher = Pattern.compile("#([A-Za-z0-9_-]+)").matcher(hashtagintitle)
        while (matcher.find()) {

            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    val spanned = (textView as TextView).text as Spanned
                    val ht = textView.text.subSequence(spanned.getSpanStart(this), spanned.getSpanEnd(this))
                    Log.d("htht12", "$ht")
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = ContextCompat.getColor(context, R.color.login_btn_before_click)
                }
            }
            hashtagintitle.setSpan(clickableSpan,
                matcher.start(),
                matcher.end(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        holder.description.text = hashtagintitle
        holder.description.movementMethod = LinkMovementMethod.getInstance()
        val postImageUrls = (postList[position].files!!)
        if (!postImageUrls.isNullOrEmpty()) {
            holder.postImage.visibility = View.VISIBLE
            Glide.with(holder.postImage.context).load(postImageUrls[0].url)
                .into(holder.postImage)
        }
    }

    override fun getItemCount() = postList.size
}