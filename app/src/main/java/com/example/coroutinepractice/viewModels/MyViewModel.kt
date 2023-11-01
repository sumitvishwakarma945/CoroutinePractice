package com.example.coroutinepractice.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.IncidentResponse
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.utils.ResponseUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel:ViewModel() {
    private val myRepository = MyRepository()
//    val _comments = MutableLiveData<List<Comments>>()
//    val comments:LiveData<List<Comments>> = _comments
    private val _comments = MutableLiveData<Comments>()
    val comments:LiveData<Comments> = _comments

     val incidents:MutableLiveData<Resource<IncidentResponse>> = MutableLiveData()
//    val incidents: LiveData<Resource<IncidentResponse>> = _incidents


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
            incidents.postValue(Resource.Loading())
            val data = myRepository.getIncidents()
//            incidents.postValue(handleIncidentResponse(data))
            incidents.postValue(ResponseUtils<IncidentResponse>().handleResponse(data))
//            if (data.body()!=null && data.isSuccessful){
//                _incidents.postValue(data.body())
//            }
        }

    /*private fun handleIncidentResponse(response: Response<IncidentResponse>) : Resource<IncidentResponse>{
        if(response.isSuccessful){
            response.body()?.let {incidentResponse ->
                return Resource.Success(incidentResponse)
            }
        }
        return Resource.Error(response.message())
    }*/
}