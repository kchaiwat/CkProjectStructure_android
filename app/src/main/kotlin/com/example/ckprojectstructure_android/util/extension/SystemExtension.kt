package com.example.ckprojectstructure_android.util.extension

import android.content.Context
import android.media.AudioManager
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration

fun Context.isSoundsEnabled(): Boolean {
    val mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    return mAudioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL
}

fun Context.hasSoftKeys(): Boolean {
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)

    return !hasMenuKey && !hasBackKey
}