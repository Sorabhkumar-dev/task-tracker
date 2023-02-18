package com.skutilityplatforms.tasktracker.data.task_scheduler

import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo

interface TaskScheduler {
    fun scheduleTask(taskInfo: TaskInfo)
    fun unScheduleTask(taskInfo: TaskInfo)
}