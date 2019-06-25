package com.co.gamesapp

interface PreferencesManager {
    fun addBooleanProperty(name: String, value: Boolean)
    fun getBooleanProperty(name: String): Boolean
}