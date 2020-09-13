package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName


data class BunqMe(

    @SerializedName("type") val type: String,
    @SerializedName("value") val value: String,
    @SerializedName("name") val name: String
)