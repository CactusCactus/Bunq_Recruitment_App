package com.kuba.bunqrecruitmentapp.api.models.api_context.session

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserPerson {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("created")
    @Expose
    var created: String? = null

    @SerializedName("updated")
    @Expose
    var updated: String? = null
}