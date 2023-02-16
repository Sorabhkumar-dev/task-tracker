package com.skutilityplatforms.tasktracker.data.room

import androidx.room.*
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun addTask(taskInfo: TaskInfo)

    @Delete
    fun deleteTask(taskInfo: TaskInfo)

    @Query("Select * From TaskInfo where task_timing > :timeMillis ")
    fun getAllTask(timeMillis:Long):Flow<List<TaskInfo>>

    @Query("Select * from TaskInfo where taskId = :taskId")
    fun getTaskInfo(taskId:Int):Flow<TaskInfo>
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskInfo: TaskInfo)
}