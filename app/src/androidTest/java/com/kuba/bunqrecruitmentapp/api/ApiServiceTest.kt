package com.kuba.bunqrecruitmentapp.api

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

class ApiServiceTest {
    @Test
    fun apiServiceInitTest() {
        val apiService = ApiService(mock(ApiInterface::class.java))
        assertNotNull(apiService)
    }
}