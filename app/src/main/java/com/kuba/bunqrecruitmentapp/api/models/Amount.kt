package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Amount (

	@SerializedName("value") val value : String,
	@SerializedName("currency") val currency : String
)