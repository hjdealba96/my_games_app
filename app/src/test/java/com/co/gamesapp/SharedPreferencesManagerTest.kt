package com.co.gamesapp

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SharedPreferencesManagerTest {

    companion object {
        private const val TEST_PROPERTY = "test-property"
    }

    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Before
    fun initPreferencesManager() {
        sharedPreferencesManager = SharedPreferencesManager(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getDefaultBooleanPropertyValue() {
        assertThat(sharedPreferencesManager.getBooleanProperty(TEST_PROPERTY)).isFalse()
    }

    @Test
    fun saveTrueBooleanProperty() {
        sharedPreferencesManager.addBooleanProperty(TEST_PROPERTY, true)
        assertThat(sharedPreferencesManager.getBooleanProperty(TEST_PROPERTY)).isTrue()
    }

    @Test
    fun saveFalseBooleanProperty() {
        sharedPreferencesManager.addBooleanProperty(TEST_PROPERTY, false)
        assertThat(sharedPreferencesManager.getBooleanProperty(TEST_PROPERTY)).isFalse()
    }

}