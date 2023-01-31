package com.bertholucci.home

import android.widget.ImageView
import coil.load

fun ImageView.loadFromUrl(image: String?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.img_splash)
    }
}

//fun ImageView.isFavorite(isFavorite: Boolean) {
//    this.setImageResource(
//        if (isFavorite) R.drawable.search_ic_heart_filled
//        else R.drawable.search_ic_heart
//    )
//}
