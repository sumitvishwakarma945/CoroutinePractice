package com.example.coroutinepractice.api

import com.example.coroutinepractice.data.Comments
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("/comments")
    suspend fun getComments():Response<List<Comments>>

}