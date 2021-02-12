package com.dionep.ribscage.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import com.google.gson.Gson

class Prefs(
    private val context: Context,
    private val gson: Gson
) {

    companion object {
        private const val APP_PREFS_NAME = "app_prefs"
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    private val appPreferences by lazy {
        context.getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE)
    }

    var authToken: String?
        get() = appPreferences.getString(KEY_AUTH_TOKEN, null)
        set(value) = appPreferences.edit { putString(KEY_AUTH_TOKEN, value) }

}