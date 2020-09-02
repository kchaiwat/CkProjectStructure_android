package com.example.ckprojectstructure_android.util.extension

import android.graphics.Paint
import android.text.SpannableString
import android.widget.TextView

fun TextView.underLine(): TextView {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    return this
}

fun TextView.setHighlightOfWord(fullText: String, keyWord: String) {
    if (fullText == "") {
        this.text = fullText
        return
    }

    if (keyWord == "") {
        this.text = fullText
        return
    }

    var startIndex = fullText.indexOf(keyWord)
    var endIndex = startIndex + keyWord.length

    if (startIndex == -1) {
        val keyWordUpperCase = keyWord.toUpperCase()
        startIndex = fullText.indexOf(keyWordUpperCase)
        endIndex = startIndex + keyWordUpperCase.length
    }

    if (startIndex == -1) {
        val keyWordLowerCase = keyWord.toLowerCase()
        startIndex = fullText.indexOf(keyWordLowerCase)
        endIndex = startIndex + keyWordLowerCase.length
    }

    if (startIndex == -1) {
        this.text = fullText
        return
    }

    val spannableString = SpannableString(fullText)
//    spannableString.setSpan(
//        BackgroundColorSpan(
//            ContextCompat.getColor(
//                this.context,
//                R.color.colorHighlight
//            )
//        ), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//    )
    this.text = spannableString
}

