package com.example.coroutinepractice.dataBuilder

import com.example.coroutinepractice.api.MyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //const val BASE_URL = "https://jsonplaceholder.typicode.com"
    private const val BASE_URL = "https://gmmco--tst2.custhelp.com"

    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
        //We can add timeout setting here
    }.build()

    val retrofit: MyApi by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)
    }
}