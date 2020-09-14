package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class AliasShort (
	@SerializedName("type") val type: ContactType,
	@SerializedName("value") val value: String,
	@SerializedName("name") val name: String
)

enum class ContactType {
	EMAIL, PHONE
}