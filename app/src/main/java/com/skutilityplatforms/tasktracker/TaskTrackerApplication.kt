package com.skutilityplatforms.tasktracker

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.skutilityplatforms.tasktracker.ui.notifications.NotificationServiceImpl
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TaskTrackerApplication : Application() {

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationServiceImpl.TASK_NOTIFICATION_ID,
            getString(R.string.task_reminder),
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = getString(R.string.used_to_reminder_about_current_task_to_user)
        notificationManager.createNotificationChannel(channel)
    }
}