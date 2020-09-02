package com.example.ckprojectstructure_android.util.delegate

import android.view.View

typealias OnItemClickListener<T> = (View, T, Int) -> Unit
typealias OnItemLongClickListener<T> = (View, T, Int) -> Unit
typealias OnCheckedChangeListener<T> = (v: View, T, isChecked: Boolean) -> Unit