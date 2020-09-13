package com.kuba.bunqrecruitmentapp.di

import android.os.Build
import androidx.core.os.BuildCompat
import com.google.gson.Gson
import com.kuba.bunqrecruitmentapp.BuildConfig
import com.kuba.bunqrecruitmentapp.api.ApiInterface
import com.kuba.bunqrecruitmentapp.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideApiInterface(gsonConverterFactory: GsonConverterFactory, callAdapterFactory: RxJava2CallAdapterFactory): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build().create(ApiInterface::class.java)
    }

    @Provides
    fun provideCountriesService(apiInterface: ApiInterface): ApiService {
        return ApiService(apiInterface)
    }

    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}