package com.kuba.bunqrecruitmentapp.api

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

class ApiServiceTest {
    @Test
    fun apiServiceInitTest() {
        val apiService = ApiService(mock(ApiInterface::class.java))
        assertNotNull(apiService)
    }
}