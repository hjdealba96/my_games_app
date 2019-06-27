package com.co.gamesapp.data.source

import com.co.gamesapp.SharedPreferencesManager
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalUserDataSource {

    companion object {
        private const val GET_STARTED = "get-started"
    }

    @Test
    fun test1() {
        //userLocalUserDataSource.addBooleanProperty(GET_STARTED, true)
        assertEquals(true, true)
    }

}