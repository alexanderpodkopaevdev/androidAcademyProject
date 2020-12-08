package com.alexanderpodkopaev.androidacademyproject.utils

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

object UiUtils {
    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels// / displayMetrics.density
        return (screenWidthDp / columnWidthDp).toInt()
    }
}

