package com.kuba.bunqrecruitmentapp.api.models.api_context.installation

import com.google.gson.annotations.SerializedName

data class InstallationResponse(
    @SerializedName("Response") val response : List<InstallationData>
)