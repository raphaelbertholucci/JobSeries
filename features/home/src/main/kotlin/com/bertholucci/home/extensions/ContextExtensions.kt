package com.bertholucci.home.extensions

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.appcompat.app.AlertDialog
import com.bertholucci.home.R

fun Context.showSortAlert(@ArrayRes arrayId: Int, callback: (Int) -> Unit) {
    AlertDialog.Builder(this).apply {
        setTitle(R.string.favorites_sort_by)
        setItems(arrayId) { dialog, which ->
            callback.invoke(which)
            dialog.dismiss()
        }
    }.create().show()
}
