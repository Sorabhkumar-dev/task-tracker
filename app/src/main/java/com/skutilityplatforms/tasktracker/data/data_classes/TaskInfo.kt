package com.skutilityplatforms.tasktracker.data.data_classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class TaskInfo(
    @PrimaryKey(autoGenerate = true) val taskId: Int,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo(name = "task_description") val taskDescription: String,
    @ColumnInfo(name = "task_timing") val taskTiming: Date
)