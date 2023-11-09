package com.example.coroutinepractice.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.coroutinepractice.R


class NotificationHelper(context: Context) {
    private val mContext = context
    private val notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object{
        const val CHANNEL_ID = "MyChannel"
    }

    private fun createChannel(){
        val channel = NotificationChannel(CHANNEL_ID, "CoroutineNotification", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "Checking local notification"
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(){
        createChannel()
        val notification = NotificationCompat.Builder(mContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_electric_bike_24)
            .setContentTitle("New Notification")
            .setContentText("This is local notification")
            .build()

        notificationManager.notify(1, notification)
    }

}