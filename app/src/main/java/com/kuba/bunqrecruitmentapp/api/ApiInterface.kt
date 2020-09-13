package com.kuba.bunqrecruitmentapp.api

import com.kuba.bunqrecruitmentapp.api.models.Payment
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {
    @GET("user/{userID}/monetary-account/{monetary-accountID}/payment")
    fun getAllPayments(
        @Header("User-Agent") userAgent: String,
        @Header("X-Bunq-Client-Authentication") authToken: String,
        @Path("userID") userId: String,
        @Path("monetary-accountID") accountId: String
    ): Observable<List<Payment>>
}