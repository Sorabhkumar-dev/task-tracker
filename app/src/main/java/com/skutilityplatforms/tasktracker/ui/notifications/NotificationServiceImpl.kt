package com.skutilityplatforms.tasktracker.ui.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigTextStyle
import com.skutilityplatforms.tasktracker.R
import com.skutilityplatforms.tasktracker.data.utility.Constants
import com.skutilityplatforms.tasktracker.ui.activity.MainActivity
import javax.inject.Inject

class NotificationServiceImpl @Inject constructor(
    private val context: Context,
    private val notificationManager: NotificationManager
) : NotificationService {

    override fun showNotification(
        taskId: Int,
        taskTitle: String,
        taskDescription: String,
        taskTiming: String
    ) {
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(Constants.TASK_ID, taskId)
        }
        val activityPendingIntent =
            PendingIntent.getActivity(context, 1, activityIntent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, TASK_NOTIFICATION_ID)
            .setAutoCancel(false)
            .setSmallIcon(R.drawable.baseline_task_24)
            .setContentTitle(taskTitle)
            .setContentText(context.getText(R.string.you_have_new_task_to_do))
            .setStyle(BigTextStyle().bigText(taskDescription))
            .setContentIntent(activityPendingIntent)
            .build()
        notificationManager.notify(1, notification)
    }

    companion object {
        const val TASK_NOTIFICATION_ID = "106"
    }
}