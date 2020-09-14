package com.kuba.bunqrecruitmentapp.di.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kuba.bunqrecruitmentapp.ui.payments.PaymentsViewModel
import com.kuba.bunqrecruitmentapp.ui.payments.new_payment.NewPaymentViewModel
import com.kuba.bunqrecruitmentapp.ui.start_session.StartSessionViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartSessionViewModel::class)
    abstract fun bindStartSessionViewModel(startSessionViewModel: StartSessionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentsViewModel::class)
    abstract fun bindPaymentsViewModel(paymentsViewModel: PaymentsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewPaymentViewModel::class)
    abstract fun bindNEwPaymentViewModel(newPaymentViewModel: NewPaymentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory

    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}