package com.skutilityplatforms.tasktracker.data.repo

import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun addTask(taskInfo: TaskInfo)

    fun deleteTask(taskInfo: TaskInfo)

    fun getAllTask(timeMillis: Long): Flow<List<TaskInfo>>

    fun getTaskInfo(taskId: Int): Flow<TaskInfo>

    fun updateTask(taskInfo: TaskInfo)
}