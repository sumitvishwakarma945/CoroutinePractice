package com.example.coroutinepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutinepractice.databinding.ActivityMainBinding
import com.example.coroutinepractice.ui.home.HomeActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)
        }
    }


}