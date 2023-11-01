package com.example.coroutinepractice.responses

import com.google.gson.annotations.SerializedName

//class Incident {
//    @SerializedName("lead_id")
//    val lead_id:String?= null
//
//    @SerializedName("asset_id")
//    val asset_id:String? = null
//
//    @SerializedName("asset_name")
//    val asset_name:String? = null
//}

data class Incident(
    @SerializedName("lead_id")
    val lead_id:String? = null,

    @SerializedName("asset_id")
    val asset_id:String? = null,

    @SerializedName("asset_name")
    val asset_name:String? = null
)
