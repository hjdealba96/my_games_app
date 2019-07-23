package com.co.sunflowerslang.gamesapp

interface PreferencesManager {
    fun addBooleanProperty(name: String, value: Boolean)
    fun getBooleanProperty(name: String): Boolean
}