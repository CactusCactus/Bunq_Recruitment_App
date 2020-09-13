package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Geolocation (

	@SerializedName("latitude") val latitude : Int,
	@SerializedName("longitude") val longitude : Int,
	@SerializedName("altitude") val altitude : Int,
	@SerializedName("radius") val radius : Int
)