package com.skutilityplatforms.tasktracker.ui.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.skutilityplatforms.tasktracker.R
import com.skutilityplatforms.tasktracker.data.utility.Constants
import com.skutilityplatforms.tasktracker.ui.notifications.NotificationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationService: NotificationService

    override fun onReceive(context: Context, intent: Intent?) {

        val taskId = intent?.getIntExtra(Constants.TASK_ID, 0) ?: 0
        val taskTitle = intent?.getStringExtra(Constants.TASK_TITLE)
            ?: context.getString(R.string.task_reminder)
        val taskDescription = intent?.getStringExtra(Constants.TASK_DESCRIPTION)
            ?: context.getString(R.string.you_have_new_task_to_do)
        val taskTiming =
            intent?.getStringExtra(Constants.TASK_TIMING) ?: context.getString(R.string.now)

        notificationService.showNotification(taskId, taskTitle, taskDescription, taskTiming)
    }
}