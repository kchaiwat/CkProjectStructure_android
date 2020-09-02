package com.example.ckprojectstructure_android.util.extension

import android.os.Build
import android.os.Environment
import android.text.Html
import android.text.Spanned
import com.example.ckprojectstructure_android.R
import com.google.gson.JsonParser
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

fun String.isCurrentDate(): Boolean {
    val thaiPatten = this.split(" ")
    return if (thaiPatten.isNotEmpty()) {
        val currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val thisDate = thaiPatten[0].toInt()

        currentDate == thisDate

    } else {
        false
    }
}

fun String.getHour(): Int {
    val dateSplit = this.split(" ")
    if (dateSplit.size > 3) {
        val timeSplit = dateSplit[3].split(":")
        return timeSplit[0].toInt()
    }

    return 0
}

fun String.getMinute(): Int {
    val dateSplit = this.split(" ")
    if (dateSplit.size > 3) {
        val timeSplit = dateSplit[3].split(":")
        return timeSplit[1].toInt()
    }

    return 0
}

fun String.toSpanned(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(this)
    }
}

fun String.isValidJSON(): Boolean {
    return try {
        val parser = JsonParser()
        parser.parse(this)
        true
    } catch (ex: Exception) {
        false
    }
}

fun String.writeStringAsFile(fileName: String) {
//    val context = Contextor.getInstance().context
//    val root =
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
//    try {
//        val myDir = File("$root/${context.getString(R.string.app_name)}/TextFile")
//        myDir.mkdirs()
//        val file = File(myDir, "$fileName.txt")
//        val out = FileWriter(file)
//        out.write(this)
//        out.close()
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
}

fun String.isValidMobilePhone(): Boolean {
    if (this.isEmpty() || this.length != 10) {
        return false
    }

    return true
}

fun String.isValidTelephone(): Boolean {
    if (this.isEmpty() && this.length != 9) {
        return false
    }

    return true
}

fun String.isValidThaiCardId(): Boolean {
    if (this.length != 13) {
        return false
    }

    return true
//    var sum = 0
//
//    for (i in 0..11) {
//        sum += Character.getNumericValue(this[i]) * (13 - i)
//    }
//
//    return (11 - sum % 11) % 10 == Character.getNumericValue(this[12])
}

fun String.toDecimalInt(): Int {
    return try {
        this.replace("[^\\d]".toRegex(), "").toInt()
    } catch (e: NumberFormatException) {
        0
    }
}
