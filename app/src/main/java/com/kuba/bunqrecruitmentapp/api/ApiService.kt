package com.kuba.bunqrecruitmentapp.api

import com.kuba.bunqrecruitmentapp.BuildConfig
import com.kuba.bunqrecruitmentapp.api.models.Payment
import io.reactivex.Observable
import javax.inject.Inject

class ApiService @Inject constructor(private val apiInterface: ApiInterface){

    fun getPayments() : Observable<List<Payment>> {
        return apiInterface.getAllPayments("DEV", BuildConfig.API_KEY, "", "")
    }
}