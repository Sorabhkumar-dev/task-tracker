package com.skutilityplatforms.tasktracker.ui.screen.task

import androidx.lifecycle.ViewModel
import com.skutilityplatforms.tasktracker.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {
    val tasks by lazy { taskRepository.getAllTask(System.currentTimeMillis()) }
}