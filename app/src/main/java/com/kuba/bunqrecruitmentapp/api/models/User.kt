package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("uuid") val uuid : String,
    @SerializedName("display_name") val displayName : String,
    @SerializedName("country") val country : String,
    @SerializedName("avatar") val avatar : Avatar,
    @SerializedName("public_nick_name") val publicNickName : String
)