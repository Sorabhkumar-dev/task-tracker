package com.skutilityplatforms.tasktracker.data.repo

import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.data.room.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao):TaskRepository {

    override suspend fun addTask(taskInfo: TaskInfo) = taskDao.addTask(taskInfo)

    override suspend fun deleteTask(taskInfo: TaskInfo) = taskDao.deleteTask(taskInfo)

    override suspend fun updateTask(taskInfo: TaskInfo) = taskDao.updateTask(taskInfo)

    override suspend fun getTaskInfo(taskId:Int) = taskDao.getTaskInfo(taskId)

    override suspend fun getAllTask(timeMillis: Long): Flow<List<TaskInfo>> = taskDao.getAllTask(timeMillis)

}