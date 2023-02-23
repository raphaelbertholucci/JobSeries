package com.bertholucci.home.extensions

import androidx.fragment.app.FragmentActivity

fun FragmentActivity?.onBackPressed() {
    this?.onBackPressedDispatcher?.apply {
        onBackPressed()
    }
}