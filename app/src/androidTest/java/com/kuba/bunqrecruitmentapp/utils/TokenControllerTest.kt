package com.kuba.bunqrecruitmentapp.utils

import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase

class TokenControllerTest : TestCase() {
    val tokenController = TokenController()

    fun testSaveGetInstallationToken() {
        val context = InstrumentationRegistry.getInstrumentation().context
        tokenController.saveInstallationToken(context, "TOKEN")
        val savedToken = tokenController.getInstallationToken(context)
        assertEquals(savedToken, "TOKEN")
    }


    fun testSaveGetApiToken() {
        val context = InstrumentationRegistry.getInstrumentation().context
        tokenController.saveApiToken(context, "TOKEN")
        val savedToken = tokenController.getApiToken(context)
        assertEquals(savedToken, "TOKEN")
    }

}