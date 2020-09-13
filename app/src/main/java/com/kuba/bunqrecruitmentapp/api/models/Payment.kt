package com.kuba.bunqrecruitmentapp.api.models

import com.google.gson.annotations.SerializedName

data class Payment (
	@SerializedName("id") val id : Int,
	@SerializedName("created") val created : String,
	@SerializedName("updated") val updated : String,
	@SerializedName("monetary_account_id") val monetaryAccountId : Int,
	@SerializedName("amount") val amount : Amount,
	@SerializedName("alias") val alias : Alias,
	@SerializedName("counterparty_alias") val counterpartyAlias : Alias,
	@SerializedName("description") val description : String,
	@SerializedName("type") val type : String,
	@SerializedName("sub_type") val subType : String,
	@SerializedName("bunqto_status") val bunqtoStatus : String,
	@SerializedName("bunqto_sub_status") val bunqtoSubStatus : String,
	@SerializedName("bunqto_share_url") val bunqtoShareUrl : String,
	@SerializedName("bunqto_expiry") val bunqtoExpiry : String,
	@SerializedName("bunqto_time_responded") val bunqtoTimeResponded : String,
	@SerializedName("attachment") val attachment : List<Attachment>,
	@SerializedName("merchant_reference") val merchantReference : String,
	@SerializedName("batch_id") val batchId : Int,
	@SerializedName("scheduled_id") val scheduledId : Int,
	@SerializedName("address_shipping") val addressShipping : AddressModel,
	@SerializedName("address_billing") val addressBilling : AddressModel,
	@SerializedName("geolocation") val geolocation : Geolocation,
	@SerializedName("request_reference_split_the_bill") val requestReferenceSplitTheBill : List<RequestReferenceSplitTheBill>,
	@SerializedName("balance_after_mutation") val balanceAfterMutation : Amount
)