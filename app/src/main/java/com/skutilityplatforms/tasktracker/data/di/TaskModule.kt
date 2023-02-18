package com.skutilityplatforms.tasktracker.data.di

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import androidx.room.Room
import com.skutilityplatforms.tasktracker.data.repo.TaskRepository
import com.skutilityplatforms.tasktracker.data.repo.TaskRepositoryImpl
import com.skutilityplatforms.tasktracker.data.room.TaskDao
import com.skutilityplatforms.tasktracker.data.room.TaskDataBase
import com.skutilityplatforms.tasktracker.data.task_scheduler.TaskScheduler
import com.skutilityplatforms.tasktracker.data.task_scheduler.TaskSchedulerImpl
import com.skutilityplatforms.tasktracker.data.utility.Constants
import com.skutilityplatforms.tasktracker.ui.notifications.NotificationService
import com.skutilityplatforms.tasktracker.ui.notifications.NotificationServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun providesRoomDb(context: Context): TaskDataBase =
        Room.databaseBuilder(context, TaskDataBase::class.java, Constants.TASK_DATABASE).build()

    @Provides
    @Singleton
    fun providesTaskDao(taskDataBase: TaskDataBase): TaskDao = taskDataBase.taskDao()

    @Provides
    @Singleton
    fun provideTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository =
        taskRepositoryImpl

    @Provides
    fun providesTaskScheduler(taskScheduler: TaskSchedulerImpl): TaskScheduler = taskScheduler

    @Provides
    @Singleton
    fun provideAlarmManger(context: Context): AlarmManager =
        context.getSystemService(AlarmManager::class.java)

    @Provides
    @Singleton
    fun provideNotificationManger(context: Context): NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    @Singleton
    fun providesNotificationService(notificationService: NotificationServiceImpl): NotificationService =
        notificationService
}