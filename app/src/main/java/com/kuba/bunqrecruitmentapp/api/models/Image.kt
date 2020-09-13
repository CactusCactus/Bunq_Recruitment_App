package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Image(

    @SerializedName("attachment_public_uuid") val attachmentPublicUuid: String,
    @SerializedName("content_type") val contentType: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
)