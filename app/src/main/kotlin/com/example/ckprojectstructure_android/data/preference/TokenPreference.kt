package com.example.ckprojectstructure_android.data.preference

import android.content.Context
import android.util.JsonToken
import com.example.ckprojectstructure_android.R
import com.example.ckprojectstructure_android.util.extension.convertToJsonModel
import com.example.ckprojectstructure_android.util.extension.toJsonString
import com.securepreferences.SecurePreferences

class TokenPreference(context: Context) {

    companion object {
        private const val KEY_JSON_TOKEN = "KEY_JSON_TOKEN"
    }

    private val securePreferences: SecurePreferences =
        SecurePreferences(
            context
//            context.getString(R.string.dev_name),
//            context.getString(R.string.source)
        )

    fun clear() {
        securePreferences.edit().putString(KEY_JSON_TOKEN, null).apply()
    }

    /********************************************************************************************
     *************************************** SAVE ***********************************************
     ********************************************************************************************/

    fun saveToken(jsonToken: JsonToken) {
        securePreferences.edit().putString(KEY_JSON_TOKEN, jsonToken.toJsonString()).apply()
    }

    /********************************************************************************************
     *************************************** GET ************************************************
     ********************************************************************************************/

    fun getAccessToken() {
//        return if (isHasToken()) getToken().accessToken else ""
    }

    fun getRefreshToken() {
//        return if (isHasToken()) getToken().refreshToken else ""
    }

    private fun getToken(): JsonToken {
        val result = securePreferences.getString(KEY_JSON_TOKEN, null)
        return result?.convertToJsonModel<JsonToken>()!!
    }

    private fun isHasToken(): Boolean {
        return try {
            getToken()
            true
        } catch (e: Exception) {
            false
        }
    }
}