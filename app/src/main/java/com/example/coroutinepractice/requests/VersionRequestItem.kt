package com.example.coroutinepractice.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VersionRequestItem {
    @SerializedName("Version_Name")
    @Expose
    var Version_Name: String? = null

    @SerializedName("Version_Number")
    @Expose
    var Version_Number: String? = null

    @SerializedName("Login")
    @Expose
    var Login: String? = null
}