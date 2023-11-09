package com.example.coroutinepractice

import android.content.Intent
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification


class NotificationListenerExampleService : NotificationListenerService() {
    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val statusBarNotification = sbn.notification
        val title = statusBarNotification.extras.getString("android.title")
        val intent = Intent("com.example.coroutinepractice")
        intent.putExtra("NotificationTitle",title)
        sendBroadcast(intent)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {

    }
}