package com.co.sunflowerslang.gamesapp.data

import com.co.sunflowerslang.gamesapp.data.source.DefaultUserRepository
import com.co.sunflowerslang.gamesapp.data.source.FakeUserDataSource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultUserRepositoryTest {

    private lateinit var userRepository: DefaultUserRepository

    @Before
    fun createRepository() {
        userRepository = DefaultUserRepository(FakeUserDataSource())
    }

    @Test
    fun test_initialStartStatus() {
        val savedStatus = userRepository.getStartStatus()
        assertEquals(false, savedStatus)
    }

    @Test
    fun test_saveStartStatusTrue() {
        userRepository.saveStart(started = true)
        val savedStatus = userRepository.getStartStatus()
        assertEquals(true, savedStatus)
    }

    @Test
    fun test_saveStartStatusFalse() {
        userRepository.saveStart(started = false)
        val savedStatus = userRepository.getStartStatus()
        assertEquals(false, savedStatus)
    }

}