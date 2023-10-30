package com.example.coroutinepractice.responses


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("columnNames")
    val columnNames: List<String>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("rows")
    val rows: List<Incident>,
    @SerializedName("tableName")
    val tableName: String
)