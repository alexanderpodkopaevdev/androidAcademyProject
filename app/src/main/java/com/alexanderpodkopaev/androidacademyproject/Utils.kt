package com.alexanderpodkopaev.androidacademyproject

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun calculateNoOfColumns(
    context: Context?,
    columnWidthDp: Float
): Int {
    val displayMetrics: DisplayMetrics? = context?.resources?.displayMetrics
    displayMetrics?.let {
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }
    return 3



}

class CharacterItemDecoration(val offset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = offset/2
        outRect.right = offset/2
    }
}
