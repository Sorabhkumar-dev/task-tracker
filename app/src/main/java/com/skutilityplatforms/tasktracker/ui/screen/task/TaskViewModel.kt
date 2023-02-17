package com.skutilityplatforms.tasktracker.ui.screen.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {
    val tasks: MutableState<List<TaskInfo>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            taskRepository.getAllTask(System.currentTimeMillis()).collect {
                tasks.value = it
            }
        }
    }
}