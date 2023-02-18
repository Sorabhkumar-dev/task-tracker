package com.skutilityplatforms.tasktracker.data.task_scheduler

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.data.utility.Constants
import com.skutilityplatforms.tasktracker.ui.receivers.AlarmReceiver
import com.skutilityplatforms.tasktracker.ui.utils.Util
import javax.inject.Inject

class TaskSchedulerImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    private val context: Context
) : TaskScheduler {

    override fun scheduleTask(taskInfo: TaskInfo) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(Constants.TASK_ID, taskInfo.taskId)
            putExtra(Constants.TASK_TITLE, taskInfo.taskTitle)
            putExtra(Constants.TASK_DESCRIPTION, taskInfo.taskDescription)
            putExtra(Constants.TASK_TIMING, Util.getFormattedDate(taskInfo.taskTiming))
        }
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            taskInfo.taskTiming.time,
            PendingIntent.getBroadcast(
                context,
                taskInfo.taskId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun unScheduleTask(taskInfo: TaskInfo) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context, taskInfo.taskId,
                Intent(), PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}