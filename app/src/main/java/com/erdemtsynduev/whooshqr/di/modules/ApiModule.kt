package com.erdemtsynduev.whooshqr.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.erdemtsynduev.whooshqr.network.NetworkApi
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule {
    @Provides
    @Singleton
    fun provideNetworkApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }
}
