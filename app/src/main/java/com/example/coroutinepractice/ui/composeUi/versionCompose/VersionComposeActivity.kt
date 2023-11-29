package com.example.coroutinepractice.ui.composeUi.versionCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class VersionComposeActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            VersionScreenFlow()
//            VersionScreen()
        }
    }
}