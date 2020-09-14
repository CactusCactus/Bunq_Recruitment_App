package com.kuba.bunqrecruitmentapp.api.models.api_context.session

import com.google.gson.annotations.SerializedName

data class SessionBody(
    @SerializedName("secret") val apiKey: String
)