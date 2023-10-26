package com.example.coroutinepractice.api

import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.requests.VersionRequestItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MyApi {

    @GET("/comments")
    suspend fun getComments():Response<List<Comments>>

    @POST("cgi-bin/gmmco.cfg/php/custom/appversioncheck.php")
    suspend fun getAppVersion(@Body versionRequestItem: VersionRequestItem, @Header("Authorization") auth:String):Response<Comments>

}