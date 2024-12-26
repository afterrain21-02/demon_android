package com.example.lab9.extenstion

import android.widget.ImageView
import com.example.lab9.R
import com.squareup.picasso.Picasso

fun ImageView.fetchImage(url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_placeholder_error)
        .into(this)
}