package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class RequestReferenceSplitTheBill (

	@SerializedName("type") val type : String,
	@SerializedName("id") val id : Int
)