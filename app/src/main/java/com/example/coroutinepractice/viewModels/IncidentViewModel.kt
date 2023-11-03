package com.example.coroutinepractice.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.responses.IncidentResponse
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.utils.ResponseUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncidentViewModel:ViewModel() {

    private val myRepository = MyRepository()
    val incidentsResponse:MutableLiveData<Resource<IncidentResponse>> = MutableLiveData()

    suspend fun getIncidents()=viewModelScope.launch(Dispatchers.IO){
        incidentsResponse.postValue(Resource.Loading())
        val data = myRepository.getIncidents()
        incidentsResponse.postValue(ResponseUtils<IncidentResponse>().handleResponse(data))
    }
}