package com.kuba.bunqrecruitmentapp.utils

import android.content.Context
import com.kuba.bunqrecruitmentapp.constants.API_CONTEXT_SP
import com.kuba.bunqrecruitmentapp.constants.API_TOKEN
import com.kuba.bunqrecruitmentapp.constants.INSTALLATION_TOKEN

class TokenController {
    fun saveInstallationToken(context: Context, token: String?) {
        context.getSharedPreferences(API_CONTEXT_SP, Context.MODE_PRIVATE).edit().putString(
            INSTALLATION_TOKEN, token
        ).apply()
    }

    fun getInstallationToken(context: Context) =
        context.getSharedPreferences(API_CONTEXT_SP, Context.MODE_PRIVATE).getString(
            INSTALLATION_TOKEN, null
        )

    fun saveApiToken(context: Context, token: String?) {
        context.getSharedPreferences(API_CONTEXT_SP, Context.MODE_PRIVATE).edit().putString(
            API_TOKEN, token
        ).apply()
    }

    fun getApiToken(context: Context) =
        context.getSharedPreferences(API_CONTEXT_SP, Context.MODE_PRIVATE).getString(
            API_TOKEN, null
        )
}