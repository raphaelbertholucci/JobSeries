package com.bertholucci.home.extensions

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import coil.load
import com.bertholucci.home.R

fun ImageView.loadFromUrl(image: String?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.img_splash)
    }
}

fun View.gone() {
    isVisible = false
}

//fun ImageView.isFavorite(isFavorite: Boolean) {
//    this.setImageResource(
//        if (isFavorite) R.drawable.search_ic_heart_filled
//        else R.drawable.search_ic_heart
//    )
//}
