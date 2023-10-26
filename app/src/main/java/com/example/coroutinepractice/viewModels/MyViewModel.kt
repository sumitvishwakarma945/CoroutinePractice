package com.example.coroutinepractice.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.responses.Comments
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.requests.VersionRequestItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel():ViewModel() {
    private val myRepository = MyRepository()
//    val _comments = MutableLiveData<List<Comments>>()
//    val comments:LiveData<List<Comments>> = _comments
    private val _comments = MutableLiveData<Comments>()
    val comments:LiveData<Comments> = _comments


    suspend fun getComments(versionRequestItem: VersionRequestItem) =
        viewModelScope.launch(Dispatchers.IO) {
//            val data = myRepository.getComments()
            val data = myRepository.getAppVersion(versionRequestItem)
            if(data.body()!=null && data.isSuccessful){
                _comments.postValue(data.body())
            }
        }
}