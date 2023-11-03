package com.example.coroutinepractice.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.responses.IncidentResponse
import com.example.coroutinepractice.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val myRepository = MyRepository()
    private val _comments = MutableLiveData<Comments>()
    val comments: LiveData<Comments> = _comments
    val incidents: MutableLiveData<Resource<IncidentResponse>> = MutableLiveData()

    suspend fun getComments(versionRequestItem: VersionRequestItem) =
        viewModelScope.launch(Dispatchers.IO) {
            val data = myRepository.getAppVersion(versionRequestItem)
            if (data.body() != null && data.isSuccessful) {
                _comments.postValue(data.body())
            }
        }

}