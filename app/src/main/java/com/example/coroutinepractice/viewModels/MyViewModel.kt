package com.example.coroutinepractice.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinepractice.data.Comments
import com.example.coroutinepractice.repository.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel:ViewModel() {
    val myRepository = MyRepository()
    val _comments = MutableLiveData<List<Comments>>()
    val comments:LiveData<List<Comments>> = _comments

    suspend fun getComments() =
        viewModelScope.launch(Dispatchers.IO) {
            val data = myRepository.getComments()
            if(data.body()!=null && data.isSuccessful){
                _comments.postValue(data.body())
            }
        }
}