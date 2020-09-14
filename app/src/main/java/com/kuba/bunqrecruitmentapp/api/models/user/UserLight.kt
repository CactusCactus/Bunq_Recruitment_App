package com.kuba.bunqrecruitmentapp.api.models.user

import com.google.gson.annotations.SerializedName
import com.kuba.bunqrecruitmentapp.api.models.AddressModel
import com.kuba.bunqrecruitmentapp.api.models.Alias
import com.kuba.bunqrecruitmentapp.api.models.Avatar

data class UserLight (

	@SerializedName("first_name") val first_name : String,
	@SerializedName("middle_name") val middle_name : String,
	@SerializedName("last_name") val last_name : String,
	@SerializedName("public_nick_name") val public_nick_name : String,
	@SerializedName("address_main") val address_main : AddressModel,
	@SerializedName("address_postal") val address_postal : AddressModel,
	@SerializedName("social_security_number") val social_security_number : String,
	//@SerializedName("tax_resident") val tax_resident : List<Tax_resident>,
	@SerializedName("date_of_birth") val date_of_birth : String,
	@SerializedName("place_of_birth") val place_of_birth : String,
	@SerializedName("country_of_birth") val country_of_birth : String,
	@SerializedName("nationality") val nationality : String,
	@SerializedName("language") val language : String,
	@SerializedName("region") val region : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("status") val status : String,
	@SerializedName("sub_status") val sub_status : String,
	@SerializedName("legal_guardian_alias") val legal_guardian_alias : Alias,
	@SerializedName("session_timeout") val session_timeout : Int,
	//@SerializedName("daily_limit_without_confirmation_login") val daily_limit_without_confirmation_login : Daily_limit_without_confirmation_login,
	@SerializedName("id") val id : Int,
	@SerializedName("created") val created : String,
	@SerializedName("updated") val updated : String,
	@SerializedName("public_uuid") val public_uuid : String,
	@SerializedName("legal_name") val legal_name : String,
	@SerializedName("display_name") val display_name : String,
	@SerializedName("alias") val alias : List<Alias>,
	@SerializedName("avatar") val avatar : Avatar,
	@SerializedName("version_terms_of_service") val version_terms_of_service : String,
	//@SerializedName("notification_filters") val notification_filters : List<Notification_filters>,
	@SerializedName("deny_reason") val deny_reason : String
	//@SerializedName("relations") val relations : List<Relations>
)