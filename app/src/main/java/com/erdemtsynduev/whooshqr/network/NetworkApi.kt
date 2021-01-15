package com.erdemtsynduev.whooshqr.network

import com.erdemtsynduev.whooshqr.network.model.response.StatusResponse
import retrofit2.http.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface NetworkApi {

    @GET("/challenge/getinfo")
    fun getStatusWooshBike(@Query("code") code: String): Observable<StatusResponse>
}