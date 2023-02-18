package com.skutilityplatforms.tasktracker.ui.notifications

interface NotificationService {
    fun showNotification(taskId:Int,taskTitle:String, taskDescription: String, taskTiming:String)
}