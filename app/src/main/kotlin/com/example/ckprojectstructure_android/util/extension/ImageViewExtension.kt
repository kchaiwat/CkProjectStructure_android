package com.example.ckprojectstructure_android.util.extension

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.ckprojectstructure_android.data.preference.TokenPreference

/*******************************************************************************************
 ************************************ Private Method ***************************************
 *******************************************************************************************/

// https://proandroiddev.com/replace-progressdialog-with-a-progress-button-in-your-app-14ed1d50b44
private fun provideCircularProgressDrawable(
    context: Context,
    width: Int
): CircularProgressDrawable {
    var strokeWidth = (width / 30).toFloat()
    var centerRadius = (width / 15).toFloat()

    strokeWidth = if (strokeWidth > 20) {
        20f
    } else {
        strokeWidth
    }

    centerRadius = if (centerRadius > 100) {
        100f
    } else {
        centerRadius
    }

    if (strokeWidth == 0f) {
        strokeWidth = 20f
    }

    if (centerRadius == 0f) {
        centerRadius = 100f
    }

    return CircularProgressDrawable(context).apply {
        this.strokeWidth = strokeWidth
        this.centerRadius = centerRadius
//        this.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccentYellow))
    }
}

private fun loadImage(
    imageView: ImageView,
    url: String,
    isSkipMemoryCache: Boolean,
    isReSize: Boolean
) {
    val vto = imageView.viewTreeObserver
    vto.addOnPreDrawListener(object : OnPreDrawListener {
        @SuppressLint("CheckResult")
        override fun onPreDraw(): Boolean {
            imageView.viewTreeObserver.removeOnPreDrawListener(this)
            val width = imageView.measuredHeight
            val height = imageView.measuredWidth

            val circularProgressDrawable = provideCircularProgressDrawable(imageView.context, width)
            circularProgressDrawable.start()

            val requestOptions = RequestOptions()

            if (isReSize) {
                requestOptions.override(width, height)
            }

            requestOptions.placeholder(circularProgressDrawable)
//            requestOptions.error(R.drawable.vector_error_100dp)

            if (isSkipMemoryCache) {
                requestOptions.skipMemoryCache(true)
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
            }

            val requestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgressDrawable.stop()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgressDrawable.stop()
                    return false
                }
            }

//            GlideApp.with(imageView.context)
//                .load(url)
//                .thumbnail(0.1f)
//                .apply(requestOptions)
//                .addListener(requestListener)
//                .into(imageView)

            return true
        }
    })
}

private fun loadImageWithHeader(imageView: ImageView, url: String, isSkipMemoryCache: Boolean) {
    val tokenPreference = TokenPreference(imageView.context)
//    val headers = LazyHeaders.Builder()
//        .addHeader(Constants.AUTHORIZATION, "Bearer ${tokenPreference.getAccessToken()}").build()
//    val glideUrl = GlideUrl(url, headers)

    val vto = imageView.viewTreeObserver
    vto.addOnPreDrawListener(object : OnPreDrawListener {
        @SuppressLint("CheckResult")
        override fun onPreDraw(): Boolean {
            imageView.viewTreeObserver.removeOnPreDrawListener(this)
            val width = imageView.measuredHeight
            val height = imageView.measuredWidth

            val circularProgressDrawable =
                provideCircularProgressDrawable(imageView.context, imageView.width)
            circularProgressDrawable.start()

            val requestOptions = RequestOptions()
                .override(width, height)
                .placeholder(circularProgressDrawable)
//                .error(R.drawable.vector_error_100dp)

            if (isSkipMemoryCache) {
                requestOptions.skipMemoryCache(true)
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
            }

            val requestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgressDrawable.stop()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgressDrawable.stop()
                    return false
                }
            }

//            GlideApp.with(imageView.context)
//                .load(glideUrl)
//                .thumbnail(0.1f)
//                .apply(requestOptions)
//                .addListener(requestListener)
//                .into(imageView)

            return true
        }
    })
}

/*******************************************************************************************
 ************************************ Public Method ***************************************
 *******************************************************************************************/

fun ImageView.load(url: String) {
    loadImage(imageView = this, url = url, isSkipMemoryCache = false, isReSize = true)
}

fun ImageView.loadFullSize(url: String) {
    loadImage(imageView = this, url = url, isSkipMemoryCache = false, isReSize = false)
}

fun ImageView.loadWithHeader(url: String) {
    return loadImageWithHeader(imageView = this, url = url, isSkipMemoryCache = false)
}
