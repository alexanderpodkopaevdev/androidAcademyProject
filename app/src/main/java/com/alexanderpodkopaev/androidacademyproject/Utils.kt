package com.alexanderpodkopaev.androidacademyproject

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

class CharacterItemDecoration(val offset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //outRect.left = offset / 2
        outRect.right = offset / 2
    }
}
