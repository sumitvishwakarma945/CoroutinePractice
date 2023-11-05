package com.example.coroutinepractice.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.data.repository.MyRepository
import com.example.coroutinepractice.requests.VersionRequestItem
import com.example.coroutinepractice.responses.Comments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val myRepository = MyRepository()
    private val _comments = MutableLiveData<Comments>()
    val version = ObservableField<String>("")
    val comments: LiveData<Comments> = _comments

    suspend fun getComments(versionRequestItem: VersionRequestItem) =
        viewModelScope.launch(Dispatchers.IO) {
            val data = myRepository.getAppVersion(versionRequestItem)
            if (data.body() != null && data.isSuccessful) {
                _comments.postValue(data.body())
            }
        }

}