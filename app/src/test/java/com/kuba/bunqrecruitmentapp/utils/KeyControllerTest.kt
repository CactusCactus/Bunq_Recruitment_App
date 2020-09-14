package com.kuba.bunqrecruitmentapp.utils

import com.google.gson.Gson
import com.kuba.bunqrecruitmentapp.BuildConfig
import com.kuba.bunqrecruitmentapp.api.models.api_context.session.SessionBody
import junit.framework.TestCase
import org.junit.Test

class KeyControllerTest : TestCase() {

    @Test
    fun testRetrieveKeys() {
        val keyPair = KeyController.retrievePrivateKey()
        assertNotNull(keyPair)
    }
    @Test
    fun testEncryption() {
        val sessionBody = SessionBody(BuildConfig.API_KEY)
        val signature = KeyController.signBody(Gson().toJson(sessionBody))
        assertNotNull(signature)
    }
}