package com.projectstructure.ck.utilitylibrary.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.projectstructure.ck.utilitylibrary.R
import kotlinx.android.synthetic.main.view_pz_toolbar.view.*

class PzToolbar @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var onToolbarClickListener: OnToolbarClickListener? = null

    private var itemCount: Int = 0

    private var isShowBackIcon: Boolean = false
    private var isShowRightIcon: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_pz_toolbar, this, true)

        attrs?.let {
            val a = context.theme.obtainStyledAttributes(it, R.styleable.PzToolbar, defStyleAttr, 0)

            if (a.hasValue(R.styleable.PzToolbar_pz_item_count)) {
                itemCount = a.getInt(R.styleable.PzToolbar_pz_item_count, 0)
                checkLess100(itemCount)
            }

            if (a.hasValue(R.styleable.PzToolbar_pz_show_back_icon)) {
                isShowBackIcon = a.getBoolean(R.styleable.PzToolbar_pz_show_back_icon, false)
                checkShowBackIcon(isShowBackIcon)
            }

            if (a.hasValue(R.styleable.PzToolbar_pz_show_right_icon)) {
                isShowRightIcon = a.getBoolean(R.styleable.PzToolbar_pz_show_right_icon, false)
                checkShowRightIcon(isShowRightIcon)
            }

            a.recycle()
        }

        val onClickListener: View.OnClickListener = OnClickListener {
            when(it) {
                imgBackIcon -> onToolbarClickListener?.onBackClickListener()
                frmBadgeCount -> onToolbarClickListener?.onBadgeClickListener()
            }
        }

        imgBackIcon.setOnClickListener(onClickListener)
        frmBadgeCount.setOnClickListener(onClickListener)
    }

    private fun checkLess100(itemCount: Int) {
        if ((itemCount > 0)) {
            txtBadgeCount.visibility = View.VISIBLE
            txtBadgeCount.text = itemCount.toString()
        } else {
            txtBadgeCount.visibility = View.GONE
            txtBadgeCount.text = ""
        }
    }

    private fun checkShowBackIcon(isShow: Boolean) {
        if (isShow) {
            imgBackIcon.visibility = View.VISIBLE
        } else {
            imgBackIcon.visibility = View.GONE
        }
    }

    private fun checkShowRightIcon(isShow: Boolean) {
        if (isShow) {
            frmBadgeCount.visibility = View.VISIBLE
        } else {
            frmBadgeCount.visibility = View.GONE
        }
    }

    fun setItemCount(itemCount: Int) {
        checkLess100(itemCount)
    }

    fun isShowBackIcon(isShow: Boolean) {
        checkShowBackIcon(isShow)
    }

    fun setOnToolbarClickListener(onToolbarClickListener: OnToolbarClickListener) {
        this.onToolbarClickListener = onToolbarClickListener
    }

    interface OnToolbarClickListener {
        fun onBackClickListener()
        fun onBadgeClickListener()
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putBoolean("isShowBackIcon", isShowBackIcon)
        bundle.putInt("itemCount", itemCount)
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state
        if (viewState is Bundle) {
            isShowBackIcon = viewState.getBoolean("isShowBackIcon", false)
            itemCount = viewState.getInt("itemCount", 0)
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
    }
}