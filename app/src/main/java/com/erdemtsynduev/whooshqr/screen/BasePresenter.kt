package com.erdemtsynduev.whooshqr.screen

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val compositeSubscription = CompositeDisposable()

    protected fun unsubscribeOnDestroy(disposable: Disposable?) {
        compositeSubscription.add(disposable!!)
    }

    protected fun unsubscribeAllNow() {
        compositeSubscription.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}
