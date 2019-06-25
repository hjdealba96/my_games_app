package com.co.gamesapp

import android.content.Context

class SharedPreferencesManager(context: Context) : PreferencesManager {

    private val preferences =
        context.getSharedPreferences(context.getString(R.string.key_user_preferences), Context.MODE_PRIVATE)

    override fun addBooleanProperty(name: String, value: Boolean) {
        preferences.edit().putBoolean(name, value).apply()
    }

    override fun getBooleanProperty(name: String): Boolean = preferences.getBoolean(name, false)
}