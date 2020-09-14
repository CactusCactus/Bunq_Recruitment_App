package com.kuba.bunqrecruitmentapp.api.models.api_context.session

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("created")
    @Expose
    val created: String,

    @SerializedName("updated")
    @Expose
    val updated: String,

    @SerializedName("token")
    @Expose
    val token: String
)