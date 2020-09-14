package com.kuba.bunqrecruitmentapp.di

import com.kuba.bunqrecruitmentapp.di.view_model.ViewModelModule
import com.kuba.bunqrecruitmentapp.ui.payments.PaymentsFragment
import com.kuba.bunqrecruitmentapp.ui.start_session.StartSessionFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: PaymentsFragment)
    fun inject(fragment: StartSessionFragment)
}