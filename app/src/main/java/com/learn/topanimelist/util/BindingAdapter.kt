package com.learn.topanimelist.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.learn.topanimelist.R
import com.learn.topanimelist.model.AnimeModel
import com.learn.topanimelist.viewmodels.AnimeAPIStatus




@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<AnimeModel>?) {
    val adapter = recyclerView.adapter as AnimeGridAdapter
    adapter.submitList(data)
}

// change url image to image
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

// check network connection status
@BindingAdapter("networkStatus")
fun bindStatus(statusImageView: ImageView, status: AnimeAPIStatus?) {
    when (status) {
        AnimeAPIStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AnimeAPIStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        AnimeAPIStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}