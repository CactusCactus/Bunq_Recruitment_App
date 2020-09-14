package com.kuba.bunqrecruitmentapp.api

import com.kuba.bunqrecruitmentapp.api.models.api_context.device.DeviceBody
import com.kuba.bunqrecruitmentapp.api.models.api_context.installation.InstallationBody
import com.kuba.bunqrecruitmentapp.api.models.api_context.installation.InstallationResponse
import com.kuba.bunqrecruitmentapp.api.models.api_context.session.SessionBody
import com.kuba.bunqrecruitmentapp.api.models.api_context.session.SessionResponse
import com.kuba.bunqrecruitmentapp.api.models.monetary_account.MonetaryAccountResponse
import com.kuba.bunqrecruitmentapp.api.models.payment.PaymentResponse
import com.kuba.bunqrecruitmentapp.api.models.payment.PaymentSendBody
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {
    @POST("installation")
    fun postInstallation(
        @Header("User-Agent") userAgent: String,
        @Body publicKey: InstallationBody
    ): Observable<InstallationResponse>

    @POST("device-server")
    fun postDevice(
        @Header("User-Agent") userAgent: String,
        @Header("X-Bunq-Client-Authentication") token: String,
        @Body body: DeviceBody
    ): Observable<Unit>

    @POST("session-server")
    fun postSession(
        @Header("User-Agent") userAgent: String,
        @Header("X-Bunq-Client-Authentication") token: String,
        @Header("X-Bunq-Client-Signature") signature: String,
        @Body body: SessionBody
    ): Observable<SessionResponse>

    @POST("user/{userID}/monetary-account/{monetary-accountID}/payment")
    fun postPayment(
        @Header("User-Agent") userAgent: String,
        @Header("X-Bunq-Client-Authentication") authToken: String,
        @Header("X-Bunq-Client-Signature") signature: String,
        @Path("userID") userId: Int,
        @Path("monetary-accountID") accountId: Int,
        @Body paymentSendBody: PaymentSendBody
    ): Observable<Unit>

    @GET("user/{userID}/monetary-account")
    fun getMonetaryAccounts(
        @Header("User-Agent") userAgent: String,
        @Header("X-Bunq-Client-Authentication") authToken: String,
        @Path("userID") userId: Int,
        @Query("newer_id") nextPage: Int?
    ): Observable<MonetaryAccountResponse>

    @GET("user/{userID}/monetary-account/{monetary-accountID}/payment")
    fun getAllPayments(
        @Header("User-Agent") userAgent: String,
        @Header("X-Bunq-Client-Authentication") authToken: String,
        @Path("userID") userId: Int,
        @Path("monetary-accountID") accountId: Int
    ): Observable<PaymentResponse>
}