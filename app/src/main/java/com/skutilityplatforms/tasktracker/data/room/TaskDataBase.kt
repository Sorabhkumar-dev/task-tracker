package com.skutilityplatforms.tasktracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo

@Database(entities = [TaskInfo::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}