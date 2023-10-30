package com.example.coroutinepractice.responses


import com.google.gson.annotations.SerializedName

data class IncidentResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("links")
    val links: List<Link>
)