package com.example.coroutinepractice.data.repository

import com.example.coroutinepractice.QueryUtils
import com.example.coroutinepractice.api.MyApi
import com.example.coroutinepractice.base.BaseRepository
import com.example.coroutinepractice.dataBuilder.RetrofitInstance
import com.example.coroutinepractice.di.ApplicationModule
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.responses.IncidentResponse
import retrofit2.Response
import javax.inject.Inject

class MyRepository @Inject constructor():BaseRepository() {
//    private var signature = String(Base64.encode(("GMM3671:Pass1234").toByteArray(), 0)).trim()
//    private val auth = "Basic $signature"
    private val retrofitInstance:MyApi = ApplicationModule().provideRetrofitInstance()
    private val query = String.format(QueryUtils.get_incident_list, "20517", "")
    private val incident_query = String.format(query, "20517", "")

//    suspend fun getComments():Response<List<Comments>> = retrofitInstance.getComments()

    suspend fun getAppVersion(versionRequestItem: VersionRequestItem):Response<Comments> = retrofitInstance.getAppVersion(versionRequestItem, auth)

//    suspend fun getAppVersion(versionRequestItem: VersionRequestItem):Response<Comments> = RetrofitInstance.retrofit.getAppVersion(versionRequestItem, auth)

//    suspend fun getIncidents():Response<IncidentResponse> = RetrofitInstance.retrofit.getIncidents(incident_query, auth, "0")

    suspend fun getIncidents():Response<IncidentResponse> = RetrofitInstance.retrofit.getIncidents(incident_query, auth, "0")

}