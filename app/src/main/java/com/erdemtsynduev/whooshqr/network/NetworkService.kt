package com.erdemtsynduev.whooshqr.network

import com.erdemtsynduev.whooshqr.network.model.response.StatusResponse
import io.reactivex.rxjava3.core.Observable

class NetworkService(private val mNetworkApi: NetworkApi) {

    fun getStatusWooshBikeObservable(codeBike: String): Observable<StatusResponse> {
        return mNetworkApi.getStatusWooshBike(codeBike)
    }
}
