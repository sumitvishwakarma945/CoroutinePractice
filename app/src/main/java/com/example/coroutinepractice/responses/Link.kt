package com.example.coroutinepractice.responses


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("href")
    val href: String,
    @SerializedName("mediaType")
    val mediaType: String,
    @SerializedName("rel")
    val rel: String
)