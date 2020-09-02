package com.example.ckprojectstructure_android.util.extension

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.example.ckprojectstructure_android.util.delegate.ActivityBindingProperty

fun <T : ViewDataBinding> FragmentActivity.activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)