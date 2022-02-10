package com.raju.obvioustest.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raju.obvioustest.R

fun AppCompatImageView.loadImageFromUrl(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()){
        Glide.with(this)
            .load(R.drawable.placeholder)
            .centerCrop()
            .into(this)
    }else{
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(this)
    }
}

