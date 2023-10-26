package com.example.coroutinepractice.ui.home

import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.coroutinepractice.R
import com.example.coroutinepractice.base.BaseActivity

class HomeActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.home_container_view)
            }
        }

    }
}