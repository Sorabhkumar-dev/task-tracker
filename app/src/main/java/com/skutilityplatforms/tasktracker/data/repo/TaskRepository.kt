package com.skutilityplatforms.tasktracker.data.repo

import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(taskInfo: TaskInfo)

    suspend fun deleteTask(taskInfo: TaskInfo)

    suspend fun getAllTask(timeMillis: Long): Flow<List<TaskInfo>>

    suspend fun getTaskInfo(taskId: Int): Flow<TaskInfo>

    suspend fun updateTask(taskInfo: TaskInfo)
}