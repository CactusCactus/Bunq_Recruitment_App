package com.kuba.bunqrecruitmentapp.api.models.api_context.session

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("Id")
    @Expose
    val id: Id,

    @SerializedName("Token")
    @Expose
    val token: Token,

    @SerializedName("UserPerson")
    @Expose
    val userPerson: UserPerson
)