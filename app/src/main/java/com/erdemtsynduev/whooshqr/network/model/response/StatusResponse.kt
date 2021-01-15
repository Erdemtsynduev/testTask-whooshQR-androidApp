package com.erdemtsynduev.whooshqr.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatusResponse {
    @SerializedName("comments")
    @Expose
    var comments: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}