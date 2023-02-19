package com.skutilityplatforms.tasktracker.data.room

import androidx.room.*
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun addTask(taskInfo: TaskInfo)

    @Delete
    suspend fun deleteTask(taskInfo: TaskInfo)

    @Query("Select * From TaskInfo where task_timing > :timeMillis order by task_timing")
    fun getAllTask(timeMillis:Long):Flow<List<TaskInfo>>

    @Query("Select * From TaskInfo where task_timing > :timeMillis order by task_timing limit 1 ")
    fun getNextTask(timeMillis:Long):Flow<TaskInfo?>

    @Query("Select * from TaskInfo where taskId = :taskId")
    fun getTaskInfo(taskId:Int):Flow<TaskInfo>
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(taskInfo: TaskInfo)
}