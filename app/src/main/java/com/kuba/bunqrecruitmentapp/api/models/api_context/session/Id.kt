package com.kuba.bunqrecruitmentapp.api.models.api_context.session

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Id (
    @SerializedName("id")
    @Expose
    val id: Int
)