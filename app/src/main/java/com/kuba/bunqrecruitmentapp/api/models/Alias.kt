package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Alias (

	@SerializedName("iban") val iban : String,
	@SerializedName("display_name") val displayName : String,
	@SerializedName("avatar") val avatar : Avatar,
	@SerializedName("label_user") val labelUser : User,
	@SerializedName("country") val country : String,
	@SerializedName("bunq_me") val bunqMe : BunqMe,
	@SerializedName("is_light") val isLight : Boolean,
	@SerializedName("swift_bic") val swiftBic : String,
	@SerializedName("swift_account_number") val swiftAccountNumber : String,
	@SerializedName("transferwise_account_number") val transferwiseAccountNumber : String,
	@SerializedName("transferwise_bank_code") val transferwiseBankCode : String,
	@SerializedName("merchant_category_code") val merchantCategoryCode : String
)