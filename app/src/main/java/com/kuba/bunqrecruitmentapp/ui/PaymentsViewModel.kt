package com.kuba.bunqrecruitmentapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.bunqrecruitmentapp.api.ApiService
import com.kuba.bunqrecruitmentapp.api.models.Payment
import com.kuba.bunqrecruitmentapp.di.DaggerApiComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PaymentsViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    val paymentListLD = MutableLiveData<List<Payment>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchPayments() {
        compositeDisposable.add(
            apiService.getPayments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    paymentListLD.value = response
                }, { error ->

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}