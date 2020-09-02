package com.example.ckprojectstructure_android.util.glide

import android.os.Handler
import android.os.Looper
import okhttp3.HttpUrl
import java.util.*

class DispatchingProgressListener : ResponseProgressListener {

    companion object {

        private val LISTENERS: WeakHashMap<String, UiOnProgressListener> = WeakHashMap()
        private val PROGRESSES: WeakHashMap<String, Long> = WeakHashMap()

        fun forget(url: String) {
            LISTENERS.remove(url)
            PROGRESSES.remove(url)
        }

        fun expect(url: String, listener: UiOnProgressListener) {
            LISTENERS[url] = listener
        }
    }

    private var mHandler: Handler = Handler(Looper.getMainLooper())

    override fun update(url: HttpUrl, bytesRead: Long, contentLength: Long) {
        val key = url.toString()
        val listener = LISTENERS[key] ?: return

        if (contentLength <= bytesRead) {
            forget(key)
        }

        if (needsDispatch(key, bytesRead, contentLength, listener.getGranualityPercentage())) {
            mHandler.post { listener.onProgress(bytesRead, contentLength) }
        }
    }

    private fun needsDispatch(
        key: String,
        current: Long,
        total: Long,
        granularity: Float
    ): Boolean {
        if (granularity == 0f || current == 0L || total == current) {
            return true
        }
        val percent = 100f * current / total
        val currentProgress = (percent / granularity).toLong()
        val lastProgress = PROGRESSES[key]
        return if (lastProgress == null || currentProgress != lastProgress) {
            PROGRESSES[key] = currentProgress
            true
        } else {
            false
        }
    }
}