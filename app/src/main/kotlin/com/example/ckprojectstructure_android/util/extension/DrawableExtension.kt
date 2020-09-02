package com.example.ckprojectstructure_android.util.extension

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

fun Drawable.toBitmap(): Bitmap {
    return (this as BitmapDrawable).bitmap
}