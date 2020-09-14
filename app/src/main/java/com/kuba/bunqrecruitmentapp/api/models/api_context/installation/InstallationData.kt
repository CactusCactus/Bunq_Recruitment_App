package com.kuba.bunqrecruitmentapp.api.models.api_context.installation

import com.google.gson.annotations.SerializedName

data class InstallationData(
    @SerializedName("Token") val token: Token,
    @SerializedName("ServerPublicKey") val serverPublicKey: ServerPublicKey
)