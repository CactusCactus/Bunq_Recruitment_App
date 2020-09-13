package com.kuba.bunqrecruitmentapp.di

import com.kuba.bunqrecruitmentapp.ui.PaymentsViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(paymentsViewModel: PaymentsViewModel)
}