package com.example.coroutinepractice.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comments(
//    val body: String,
//    val email: String,
//    val id: Int,
//    val name: String,
//    val postId: Int
    @SerializedName("status")
    @Expose
    val status:String
)