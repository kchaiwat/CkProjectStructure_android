package com.example.ckprojectstructure_android.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    liveData.observe(this, Observer { it?.let { t -> body(t) } })
}

fun <T> LifecycleOwner.observeNullable(liveData: LiveData<T>, body: (T?) -> Unit = {}) {
    liveData.observe(this, Observer { it -> body(it) })
}