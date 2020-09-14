package com.kuba.bunqrecruitmentapp.ui.payments.new_payment

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.bunqrecruitmentapp.api.ApiService
import com.kuba.bunqrecruitmentapp.api.models.payment.PaymentSendBody
import com.kuba.bunqrecruitmentapp.utils.TokenController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewPaymentViewModel @Inject constructor(
    private val apiService: ApiService,
    private val tokenController: TokenController
) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val sendStatusLD = MutableLiveData<SendStatus>(SendStatus.NOT_STARTED)
    var _userId: Int? = null
    var _accountId: Int? = null

    fun sendPayment(context: Context, paymentSendBody: PaymentSendBody) {
        val token = tokenController.getApiToken(context)
        val userId = _userId;
        val accountId = _accountId;
        if (userId != null && accountId != null) {
            sendStatusLD.value = SendStatus.SENDING
            compositeDisposable.add(
                apiService.postPayment(token, userId, accountId, paymentSendBody)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        sendStatusLD.value = SendStatus.SUCCESS
                    }, {
                        sendStatusLD.value = SendStatus.FAIL
                        Log.e(javaClass.name, "fail in sendPayment()", it)
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}

enum class SendStatus {
    NOT_STARTED, SENDING, SUCCESS, FAIL
}