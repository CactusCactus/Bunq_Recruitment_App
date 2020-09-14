package com.kuba.bunqrecruitmentapp.api.models.api_context.device

import com.google.gson.annotations.SerializedName

data class DeviceBody(
    @SerializedName("description") val description: String,
    @SerializedName("secret") val apiKey: String,
    @SerializedName("permitted_ips") val ips: List<String>
)