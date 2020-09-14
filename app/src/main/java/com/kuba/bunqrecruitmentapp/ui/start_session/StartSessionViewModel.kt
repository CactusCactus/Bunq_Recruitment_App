package com.kuba.bunqrecruitmentapp.ui.start_session

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.bunqrecruitmentapp.R
import com.kuba.bunqrecruitmentapp.api.ApiService
import com.kuba.bunqrecruitmentapp.utils.TokenController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StartSessionViewModel @Inject constructor(
    private val apiService: ApiService,
    private val tokenController: TokenController
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var userId: Int? = null
    val statusLD = MutableLiveData<ApiContextStatus>(ApiContextStatus.NOT_STARTED)


    fun initSession(context: Context) {
        statusLD.value == ApiContextStatus.INSTALLATION
        compositeDisposable.add(apiService.postInstallation()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                val token = it.response[1].token.token
                tokenController.saveInstallationToken(context, token)
                apiService.postSessionServer(token)
                statusLD.postValue(ApiContextStatus.SESSION_DEVICE)
                apiService.postDevice(token)
            }.flatMap {
                val token = tokenController.getInstallationToken(context)
                statusLD.postValue(ApiContextStatus.SESSION_SERVER)
                apiService.postSessionServer(token)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tokenController.saveApiToken(context, it.response[1].token.token)
                userId = it.response[2].userPerson.id
                statusLD.value = ApiContextStatus.SUCCESS
            }, {
                statusLD.value = ApiContextStatus.FAIL
                Log.e(javaClass.name, "fail in initSession()", it)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}

enum class ApiContextStatus {
    NOT_STARTED, INSTALLATION, SESSION_DEVICE, SESSION_SERVER, SUCCESS, FAIL;

    fun toString(context: Context): String {
        return when (this) {
            NOT_STARTED -> context.getString(R.string.not_started)
            INSTALLATION -> context.getString(R.string.installation)
            SESSION_DEVICE -> context.getString(R.string.registering_device)
            SESSION_SERVER -> context.getString(R.string.starting_session)
            SUCCESS -> context.getString(R.string.success)
            FAIL -> context.getString(R.string.fail)
        }
    }
}