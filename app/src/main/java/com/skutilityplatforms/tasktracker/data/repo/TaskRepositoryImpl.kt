package com.skutilityplatforms.tasktracker.data.repo

import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.data.room.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao):TaskRepository {

    override fun addTask(taskInfo: TaskInfo) = taskDao.addTask(taskInfo)

    override fun deleteTask(taskInfo: TaskInfo) = taskDao.deleteTask(taskInfo)

    override fun updateTask(taskInfo: TaskInfo) = taskDao.updateTask(taskInfo)

    override fun getTaskInfo(taskId:Int) = taskDao.getTaskInfo(taskId)

    override fun getAllTask(timeMillis: Long): Flow<List<TaskInfo>> = taskDao.getAllTask(timeMillis)

}