package com.example.coroutinepractice.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.utils.Resource
import com.example.coroutinepractice.utils.ResponseUtils
import com.google.android.gms.common.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    private val myRepository = MyRepository()
    private val _comments = MutableLiveData<Comments>()
    val version = ObservableField<String>("")
    val comments: LiveData<Comments> = _comments

    private val _commentsFlow:MutableStateFlow<Resource<Comments>> = MutableStateFlow(Resource.Loading())
    val commentsFLow:StateFlow<Resource<Comments>> = _commentsFlow

    suspend fun getComments(versionRequestItem: VersionRequestItem) =
        viewModelScope.launch(Dispatchers.IO) {
            val data = myRepository.getAppVersion(versionRequestItem)
            if (data.body() != null && data.isSuccessful) {
                _comments.postValue(data.body())
            }
        }

    fun getCommentsFlow(versionRequestItem: VersionRequestItem){
        viewModelScope.launch(Dispatchers.IO) {
            val data = myRepository.getAppVersionFlow(versionRequestItem)
                .collectLatest {versionResponse ->
                    _commentsFlow.value = ResponseUtils<Comments>().handleResponse(versionResponse)
                }
        }
    }

}