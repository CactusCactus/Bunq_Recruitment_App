package com.kuba.bunqrecruitmentapp.api

import com.google.gson.Gson
import com.kuba.bunqrecruitmentapp.BuildConfig
import com.kuba.bunqrecruitmentapp.api.models.api_context.device.DeviceBody
import com.kuba.bunqrecruitmentapp.api.models.api_context.installation.InstallationBody
import com.kuba.bunqrecruitmentapp.api.models.api_context.installation.InstallationResponse
import com.kuba.bunqrecruitmentapp.api.models.api_context.session.SessionBody
import com.kuba.bunqrecruitmentapp.api.models.api_context.session.SessionResponse
import com.kuba.bunqrecruitmentapp.api.models.monetary_account.MonetaryAccountResponse
import com.kuba.bunqrecruitmentapp.api.models.payment.PaymentResponse
import com.kuba.bunqrecruitmentapp.api.models.payment.PaymentSendBody
import com.kuba.bunqrecruitmentapp.constants.USER_AGENT
import com.kuba.bunqrecruitmentapp.utils.KeyController
import io.reactivex.Observable
import javax.inject.Inject

class ApiService @Inject constructor(private val apiInterface: ApiInterface) {
    fun postInstallation(): Observable<InstallationResponse> {
        return apiInterface.postInstallation(
            USER_AGENT,
            InstallationBody(KeyController.getPEMFormattedKey())
        )
    }

    fun postDevice(token: String?): Observable<Unit> {
        val deviceBody = DeviceBody(USER_AGENT, BuildConfig.API_KEY, listOf("*"))
        token?.let {
            return apiInterface.postDevice(
                USER_AGENT,
                it,
                deviceBody
            )
        }
        return Observable.error(Throwable("Token null"))
    }

    fun postSessionServer(token: String?): Observable<SessionResponse> {
        val sessionBody = SessionBody(BuildConfig.API_KEY)
        val signature = KeyController.signBody(Gson().toJson(sessionBody))
        token?.let {
            return apiInterface.postSession(
                USER_AGENT,
                it,
                signature,
                sessionBody
            )
        }
        return Observable.error(Throwable("Token null"))
    }

    fun getPayments(token: String?, userId: Int, accountId: Int): Observable<PaymentResponse> {
        token?.let {
            return apiInterface.getAllPayments(
                USER_AGENT,
                it,
                userId,
                accountId
            )
        }
        return Observable.error(Throwable("Token null"))
    }

    fun getMonetaryAccounts(
        token: String?,
        userId: Int,
        nextPage: Int?
    ): Observable<MonetaryAccountResponse> {
        token?.let {
            return apiInterface.getMonetaryAccounts(
                USER_AGENT,
                it,
                userId,
                nextPage
            )
        }
        return Observable.error(Throwable("Token null"))
    }

    fun postPayment(
        token: String?,
        userId: Int,
        accountId: Int,
        paymentSendBody: PaymentSendBody
    ): Observable<Unit> {
        token?.let {
            val signature = KeyController.signBody(Gson().toJson(paymentSendBody))
            return apiInterface.postPayment(
                USER_AGENT,
                it,
                signature,
                userId,
                accountId,
                paymentSendBody
            )
        }
        return Observable.error(Throwable("Token null"))
    }
}