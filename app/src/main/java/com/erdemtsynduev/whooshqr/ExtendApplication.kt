package com.erdemtsynduev.whooshqr

import android.app.Application
import com.erdemtsynduev.whooshqr.di.AppComponent
import com.erdemtsynduev.whooshqr.di.DaggerAppComponent
import com.erdemtsynduev.whooshqr.di.modules.ContextModule

class ExtendApplication : Application() {

    init {
        instance = this
    }

    private var sAppComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        //Create dagger component
        sAppComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return sAppComponent
    }

    companion object {
        lateinit var instance: ExtendApplication
            private set
    }
}