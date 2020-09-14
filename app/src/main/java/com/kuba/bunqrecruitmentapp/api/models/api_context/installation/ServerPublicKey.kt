package com.kuba.bunqrecruitmentapp.api.models.api_context.installation

import com.google.gson.annotations.SerializedName

data class ServerPublicKey(
    @SerializedName("server_public_key") val value: String
)