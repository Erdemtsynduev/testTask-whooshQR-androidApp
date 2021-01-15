package com.erdemtsynduev.whooshqr.di

import android.content.Context
import com.erdemtsynduev.whooshqr.di.modules.ApiModule
import com.erdemtsynduev.whooshqr.di.modules.ContextModule
import com.erdemtsynduev.whooshqr.di.modules.NetworkModule
import com.erdemtsynduev.whooshqr.di.modules.RetrofitModule
import com.erdemtsynduev.whooshqr.network.NetworkService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, ApiModule::class, RetrofitModule::class])
interface AppComponent {
    val context: Context
    val networkService: NetworkService
}
