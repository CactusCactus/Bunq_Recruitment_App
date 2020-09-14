package com.kuba.bunqrecruitmentapp.api.models.payment

import com.google.gson.annotations.SerializedName
import com.kuba.bunqrecruitmentapp.api.models.Pagination

data class PaymentResponse(
    @SerializedName("Response")
    val response: List<Response>,
    @SerializedName("Pagination")
    val pagination: Pagination
)