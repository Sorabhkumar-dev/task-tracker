package com.skutilityplatforms.tasktracker.ui.screen.taskdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    ViewModel() {
    val taskInfoState: MutableState<TaskInfo?> = mutableStateOf(null)

    fun getTaskInfo(taskId: Int) {
        viewModelScope.launch {
            taskRepository.getTaskInfo(taskId).collect {
                taskInfoState.value = it
            }
        }
    }
}