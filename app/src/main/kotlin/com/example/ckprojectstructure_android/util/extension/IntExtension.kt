package com.example.ckprojectstructure_android.util.extension

import java.text.DecimalFormat

fun Int.toDecimalString(): String {
    val decimalFormat = DecimalFormat("###,###")
    return decimalFormat.format(this)
}