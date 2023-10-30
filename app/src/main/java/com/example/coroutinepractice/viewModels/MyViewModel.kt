package com.example.coroutinepractice.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.IncidentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel():ViewModel() {
    private val myRepository = MyRepository()
//    val _comments = MutableLiveData<List<Comments>>()
//    val comments:LiveData<List<Comments>> = _comments
    private val _comments = MutableLiveData<Comments>()
    val comments:LiveData<Comments> = _comments

    private val _incidents = MutableLiveData<IncidentResponse>()
    val incidents:LiveData<IncidentResponse> = _incidents


    suspend fun getComments(versionRequestItem: VersionRequestItem) =
        viewModelScope.launch(Dispatchers.IO) {
//            val data = myRepository.getComments()
            val data = myRepository.getAppVersion(versionRequestItem)
            if(data.body()!=null && data.isSuccessful){
                _comments.postValue(data.body())
            }
        }

    suspend fun getIncidents() =
        viewModelScope.launch(Dispatchers.IO) {
            val data = myRepository.getIncidents()
            if (data.body()!=null && data.isSuccessful){
                _incidents.postValue(data.body())
            }
        }
}