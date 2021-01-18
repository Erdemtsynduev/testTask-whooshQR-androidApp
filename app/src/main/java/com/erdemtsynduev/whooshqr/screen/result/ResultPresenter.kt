package com.erdemtsynduev.whooshqr.screen.result

import com.erdemtsynduev.whooshqr.ExtendApplication
import com.erdemtsynduev.whooshqr.network.NetworkService
import com.erdemtsynduev.whooshqr.network.model.response.ErrorResponse
import com.erdemtsynduev.whooshqr.screen.BasePresenter
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import retrofit2.HttpException
import javax.inject.Inject

@InjectViewState
class ResultPresenter(var qrDataBike: String?) : BasePresenter<ResultView>() {

    @Inject
    lateinit var networkService: NetworkService

    init {
        ExtendApplication.instance.getAppComponent()?.inject(this)
    }

    override fun onFirstViewAttach() {
        getBikeData(qrDataBike)
    }

    private fun getBikeData(qrData: String?) {
        viewState.startProgressBar()
        val subscription = networkService.getStatusWooshBikeObservable(
            qrData!!
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.stopProgressBar()
                viewState.showDataResponse(it.status, it.comments)
            }, { exception ->
                viewState.stopProgressBar()
                checkError(exception)
            })
        unsubscribeOnDestroy(subscription)
    }

    private fun checkError(exception: Throwable) {
        if (exception is HttpException) {
            val errorJsonString = exception.response()?.errorBody()!!.string()
            val gson = Gson()
            val errorModel = gson.fromJson(errorJsonString, ErrorResponse::class.java)
            if (errorModel?.message != null && errorModel.message!!.isNotEmpty()) {
                viewState.showError(errorModel.message)
                return
            }
        } else {
            viewState.showError("An unknown error has occurred")
        }
    }
}