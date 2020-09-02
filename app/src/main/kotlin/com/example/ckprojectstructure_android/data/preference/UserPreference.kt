package com.example.ckprojectstructure_android.data.preference

import android.content.Context
import com.securepreferences.SecurePreferences

class UserPreference(context: Context) {

    companion object {
//        private const val KEY_JSON_USER = "KEY_JSON_USER"
    }

    private val securePreferences: SecurePreferences =
        SecurePreferences(
            context
//            context.getString(R.string.dev_name),
//            context.getString(R.string.source)
        )

//    fun clear() {
//        securePreferences.edit().putString(KEY_JSON_USER, null).apply()
//    }

    /********************************************************************************************
     *************************************** SAVE ***********************************************
     ********************************************************************************************/

//    fun saveUser(jsonUser: JsonUser) {
//        securePreferences.edit().putString(KEY_JSON_USER, jsonUser.toJsonString()).apply()
//    }

    /********************************************************************************************
     *************************************** GET ************************************************
     ********************************************************************************************/

//    fun getUserId(): Int {
//        return if (isHasUser()) getUser().id else -1
//    }

//    fun getUser(): JsonUser {
//        val result = securePreferences.getString(KEY_JSON_USER, null)
//        return result?.convertToJsonModel<JsonUser>()!!
//    }

    fun isHasUser(): Boolean {
        return try {
//            getUser()
            true
        } catch (e: Exception) {
            false
        }
    }
}