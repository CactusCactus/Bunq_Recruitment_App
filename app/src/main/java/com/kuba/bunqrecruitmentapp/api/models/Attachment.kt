package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Attachment(

    @SerializedName("id") val id: Int,
    @SerializedName("monetary_account_id") val monetaryAccountId: Int
)