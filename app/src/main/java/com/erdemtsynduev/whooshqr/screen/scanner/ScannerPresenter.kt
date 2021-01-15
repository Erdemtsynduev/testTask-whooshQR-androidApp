package com.erdemtsynduev.whooshqr.screen.scanner

import android.net.Uri
import com.erdemtsynduev.whooshqr.screen.BasePresenter
import moxy.InjectViewState

@InjectViewState
class ScannerPresenter : BasePresenter<ScannerView>() {

    fun checkQRData(qrData: String?) {
        val uri: Uri = Uri.parse(qrData)
        val qrDataBike = uri.getQueryParameter("scooter_code")
        viewState.showResultScreen(qrDataBike)
    }
}