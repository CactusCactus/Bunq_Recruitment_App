package com.kuba.bunqrecruitmentapp.api.models.api_context.installation

import com.google.gson.annotations.SerializedName

data class InstallationBody(@SerializedName("client_public_key") val publicKey: String)