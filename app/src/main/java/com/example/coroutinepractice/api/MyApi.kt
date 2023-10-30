package com.example.coroutinepractice.api

import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.IncidentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MyApi {

    @GET("/comments")
    suspend fun getComments():Response<List<Comments>>

    @POST("cgi-bin/gmmco.cfg/php/custom/appversioncheck.php")
    suspend fun getAppVersion(@Body versionRequestItem: VersionRequestItem, @Header("Authorization") auth:String):Response<Comments>

    @GET("/services/rest/connect/latest/queryResults")
    suspend fun getIncidents(
        @Query("query") incidentQuery: String,
        @Header("Authorization") auth: String,
        @Header("OSvC-CREST-Application-Context") s: String
    ): Response<IncidentResponse>
}