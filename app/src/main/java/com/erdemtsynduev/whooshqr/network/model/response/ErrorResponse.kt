package com.erdemtsynduev.whooshqr.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse {

    @SerializedName("message")
    @Expose
    var message: String? = null
}