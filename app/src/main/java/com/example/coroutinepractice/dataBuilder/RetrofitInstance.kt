package com.example.coroutinepractice.dataBuilder

import com.example.coroutinepractice.BASE_URL
import com.example.coroutinepractice.api.MyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)
}