package com.kuba.bunqrecruitmentapp.ui.payments

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.bunqrecruitmentapp.api.ApiService
import com.kuba.bunqrecruitmentapp.api.models.payment.Payment
import com.kuba.bunqrecruitmentapp.utils.TokenController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PaymentsViewModel @Inject constructor(
    private val apiService: ApiService,
    private val tokenController: TokenController
) : ViewModel() {
    val paymentListLD = MutableLiveData<List<Payment>>()
    val nextPageLD = MutableLiveData<Int>()
    var accountId: Int? = null
    var userId: Int? = null
    private val compositeDisposable = CompositeDisposable()

    fun fetchPayments(context: Context, userId: Int) {
        val token = tokenController.getApiToken(context)
        compositeDisposable.add(
            apiService.getMonetaryAccounts(token, userId, nextPageLD.value)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap { response ->
                    accountId = response.response[0].monetaryAccountBank.id
                    accountId?.let {
                        apiService.getPayments(token, userId, it)
                    }
                }.observeOn(AndroidSchedulers.mainThread()).subscribe({
                    nextPageLD.value = it.pagination.getNewerUrlId()
                    paymentListLD.value = it.response.map { response -> response.payment }
                }, {
                    Log.e(javaClass.name, "fail in fetchPayments()", it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}