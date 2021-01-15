package com.erdemtsynduev.whooshqr.di.modules

import dagger.Module
import dagger.Provides
import com.erdemtsynduev.whooshqr.network.NetworkApi
import com.erdemtsynduev.whooshqr.network.NetworkService
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkService(networkApi: NetworkApi): NetworkService {
        return NetworkService(networkApi)
    }
}