package com.skutilityplatforms.tasktracker.data.di

import android.content.Context
import androidx.room.Room
import com.skutilityplatforms.tasktracker.data.repo.TaskRepository
import com.skutilityplatforms.tasktracker.data.repo.TaskRepositoryImpl
import com.skutilityplatforms.tasktracker.data.room.TaskDao
import com.skutilityplatforms.tasktracker.data.room.TaskDataBase
import com.skutilityplatforms.tasktracker.data.utility.Constants
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
    fun provideTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository = taskRepositoryImpl
}