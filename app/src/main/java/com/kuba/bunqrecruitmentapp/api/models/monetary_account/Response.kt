package com.kuba.bunqrecruitmentapp.api.models.monetary_account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("MonetaryAccountBank")
    @Expose
    var monetaryAccountBank: MonetaryAccount
)