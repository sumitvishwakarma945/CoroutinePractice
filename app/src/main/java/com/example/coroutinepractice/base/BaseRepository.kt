package com.example.coroutinepractice.base

import android.util.Base64
import com.example.coroutinepractice.api.MyApi
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.Comments
import retrofit2.Response

abstract class BaseRepository {
    private var signature = String(Base64.encode(("GMM3671:Pass1234").toByteArray(), 0)).trim()
    val auth = "Basic $signature"
}