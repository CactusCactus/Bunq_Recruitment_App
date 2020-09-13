package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class AddressModel (

	@SerializedName("street") val street : String,
	@SerializedName("house_number") val houseNumber : String,
	@SerializedName("po_box") val poBox : String,
	@SerializedName("postal_code") val postalCode : String,
	@SerializedName("city") val city : String,
	@SerializedName("country") val country : String,
	@SerializedName("extra") val extra : String,
	@SerializedName("mailbox_name") val mailboxName : String,
	@SerializedName("province") val province : String
)