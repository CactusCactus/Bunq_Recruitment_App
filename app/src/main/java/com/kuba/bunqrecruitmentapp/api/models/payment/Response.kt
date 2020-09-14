package com.kuba.bunqrecruitmentapp.api.models.payment

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("Payment")
    val payment: Payment
)