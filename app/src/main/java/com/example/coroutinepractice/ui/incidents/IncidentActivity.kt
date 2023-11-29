package com.example.coroutinepractice.ui.incidents

import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.coroutinepractice.R
import com.example.coroutinepractice.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.incident_activity)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<IncidentFragment>(R.id.incident_container_view)
            }
        }
    }
}