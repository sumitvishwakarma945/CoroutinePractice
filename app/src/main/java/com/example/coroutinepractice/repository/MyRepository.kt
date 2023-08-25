package com.example.coroutinepractice.repository

import com.example.coroutinepractice.api.MyApi
import com.example.coroutinepractice.data.Comments
import com.example.coroutinepractice.dataBuilder.RetrofitInstance
import retrofit2.Response

class MyRepository {

    suspend fun getComments():Response<List<Comments>> = RetrofitInstance.retrofit.getComments()

}