package com.kuba.bunqrecruitmentapp.api.models.payment

import com.google.gson.annotations.SerializedName
import com.kuba.bunqrecruitmentapp.api.models.AliasShort
import com.kuba.bunqrecruitmentapp.api.models.Amount

data class PaymentSendBody(
    @SerializedName("amount") val amount: Amount,
    @SerializedName("counterparty_alias") val alias: AliasShort,
    @SerializedName("description") val description: String
)