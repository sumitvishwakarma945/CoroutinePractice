package com.example.coroutinepractice.utils

import com.example.coroutinepractice.responses.IncidentResponse
import retrofit2.Response

class ResponseUtils<T> {

    fun handleResponse(response: Response<T>) : Resource<T>{
        if(response.isSuccessful){
            response.body()?.let {genericResponse ->
                return Resource.Success(genericResponse)
            }
        }
        return Resource.Error(response.message())
    }

}