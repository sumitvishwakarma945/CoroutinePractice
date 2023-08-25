package com.example.coroutinepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coroutinepractice.api.MyApi
import com.example.coroutinepractice.viewModels.MyViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com"

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dummyTv: TextView = findViewById(R.id.tvDummy)
        var textForDummyTV = ""
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        lifecycleScope.launch(Dispatchers.Main) {
            myViewModel.getComments()
            myViewModel.comments.observe(this@MainActivity, Observer {
                textForDummyTV = it[1].toString()
                dummyTv.text = textForDummyTV
            })

        }
    }

}