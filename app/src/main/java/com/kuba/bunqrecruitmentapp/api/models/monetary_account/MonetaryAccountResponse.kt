package com.kuba.bunqrecruitmentapp.api.models.monetary_account

import com.google.gson.annotations.SerializedName

data class MonetaryAccountResponse(
    @SerializedName("Response")
    val response: List<Response>
)