package com.example.coroutinepractice.data.repository

import android.util.Base64
import com.example.coroutinepractice.base.BaseRepository
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.dataBuilder.RetrofitInstance
import com.example.coroutinepractice.requests.VersionRequestItem
import retrofit2.Response

class MyRepository:BaseRepository() {
//    private var signature = String(Base64.encode(("GMM3671:Pass1234").toByteArray(), 0)).trim()
//    private val auth = "Basic $signature"

    suspend fun getComments():Response<List<Comments>> = RetrofitInstance.retrofit.getComments()

    suspend fun getAppVersion(versionRequestItem: VersionRequestItem):Response<Comments> = RetrofitInstance.retrofit.getAppVersion(versionRequestItem, auth)

}