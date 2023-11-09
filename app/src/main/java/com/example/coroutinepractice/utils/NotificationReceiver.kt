package com.example.coroutinepractice.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class NotificationReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.extras?.getString("NotificationTitle")

    }

}