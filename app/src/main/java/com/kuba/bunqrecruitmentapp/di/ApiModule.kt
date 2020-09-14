package com.kuba.bunqrecruitmentapp.di

import com.google.gson.Gson
import com.kuba.bunqrecruitmentapp.BuildConfig
import com.kuba.bunqrecruitmentapp.api.ApiInterface
import com.kuba.bunqrecruitmentapp.api.ApiService
import com.kuba.bunqrecruitmentapp.utils.TokenController
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    @Provides
    fun provideCountriesService(apiInterface: ApiInterface): ApiService {
        return ApiService(apiInterface)
    }

    @Provides
    fun provideApiInterface(
        gsonConverterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory
    ): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build().create(ApiInterface::class.java)
    }

//    @Provides
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().addInterceptor { chain ->
//            val request = chain.request().newBuilder()
//                .addHeader("User-Agent", USER_AGENT)
//                .addHeader("X-Bunq-Client-Authentication", BuildConfig.API_KEY)
//                .build()
//            chain.proceed(request)
//        }.build()
//    }


    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun providesTokenController(): TokenController {
        return TokenController()
    }
}
