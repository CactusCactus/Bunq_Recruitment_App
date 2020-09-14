package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("newer_url") val newerUrl: String?,
    @SerializedName("older_url") val olderUrl: String?
) {
    fun getNewerUrlId(): Int? {
        val split: List<String>? = newerUrl?.split("newer_id=")
        return split?.get(split.lastIndex)?.toInt()
    }
    fun getOlderUrlId(): Int? {
        val split: List<String>? = olderUrl?.split("older_url=")
        return split?.get(split.lastIndex)?.toInt()
    }
}