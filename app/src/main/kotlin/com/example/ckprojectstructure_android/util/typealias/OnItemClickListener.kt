package com.example.ckprojectstructure_android.util.`typealias`

import android.view.View

typealias OnItemClickListener<T> = (View, T, Int) -> Unit
typealias OnTwoItemClickListener<T, R> = (View, T, R, Int) -> Unit