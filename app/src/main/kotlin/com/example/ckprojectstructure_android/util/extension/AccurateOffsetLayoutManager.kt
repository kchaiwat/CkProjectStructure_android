package com.example.ckprojectstructure_android.util.extension

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AccurateOffsetLayoutManager constructor(
    mContext: Context,
    @RecyclerView.Orientation mOrientation: Int,
    isReverseLayout: Boolean
) : LinearLayoutManager(mContext) {

    init {
        isSmoothScrollbarEnabled = false
        orientation = mOrientation
        reverseLayout = isReverseLayout
    }

    companion object {
        private const val SMOOTH_VALUE = 100
    }

    private var maxRange: Int = 0

    //Computes the vertical size of the scrollbar indicator (thumb)
    override fun computeVerticalScrollExtent(state: RecyclerView.State): Int {
        return if (childCount > 0 && childCount < (itemCount - 1)) {
            SMOOTH_VALUE * 3
        } else {
            0
        }
    }

    //Computes the vertical size of all the content (scrollbar track)
    override fun computeVerticalScrollRange(state: RecyclerView.State): Int {
        maxRange = Math.max((itemCount - 1) * SMOOTH_VALUE, 0)
        return maxRange
    }

    //Computes the vertical distance from the top of the screen (scrollbar position)
    override fun computeVerticalScrollOffset(state: RecyclerView.State): Int {
        return if (reverseLayout) {
            calculateReverseLayout()
        } else {
            calculateNormalLayout()
        }
    }

    private fun calculateReverseLayout(): Int {
        if (childCount <= 0) {
            return 0
        }

        if (findFirstCompletelyVisibleItemPosition() == 0) {
            return maxRange
        }

        val lastPos = findLastVisibleItemPosition()
        if (lastPos == androidx.recyclerview.widget.RecyclerView.NO_POSITION) {
            return 0
        }

        val view = findViewByPosition(lastPos) ?: return (itemCount - 1)
        // Top of the view in pixels
        val top = getDecoratedTop(view)
        val height = getDecoratedMeasuredHeight(view)

        val heightOfScreen = if (height <= 0) {
            maxRange
        } else {
            Math.abs(SMOOTH_VALUE * top / height)
        }

        return if (heightOfScreen == 0 && lastPos > 0) {
            maxRange - ((SMOOTH_VALUE * lastPos) + 1)
        } else {
            maxRange - ((SMOOTH_VALUE * lastPos) - heightOfScreen)
        }
    }

    private fun calculateNormalLayout(): Int {
        if (childCount <= 0) {
            return 0
        }

        if (findLastCompletelyVisibleItemPosition() == 0) {
            return 0
        }

        val firstPos = findFirstVisibleItemPosition()
        if (firstPos == RecyclerView.NO_POSITION) {
            return 0
        }

        val view = findViewByPosition(firstPos) ?: return 0
        // Top of the view in pixels
        val top = getDecoratedTop(view)
        val height = getDecoratedMeasuredHeight(view)

        val heightOfScreen = if (height <= 0) {
            0
        } else {
            Math.abs(SMOOTH_VALUE * top / height)
        }

        return if (heightOfScreen == 0 && firstPos > 0) {
            SMOOTH_VALUE * firstPos - 1
        } else {
            SMOOTH_VALUE * firstPos + heightOfScreen
        }
    }
}