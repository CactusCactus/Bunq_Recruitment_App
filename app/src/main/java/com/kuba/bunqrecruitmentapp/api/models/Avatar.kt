package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Avatar (

	@SerializedName("uuid") val uuid : String,
	@SerializedName("anchor_uuid") val anchorUuid : String,
	@SerializedName("image") val image : List<Image>
)