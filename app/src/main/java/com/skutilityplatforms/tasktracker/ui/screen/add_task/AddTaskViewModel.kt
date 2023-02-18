package com.skutilityplatforms.tasktracker.ui.screen.add_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.data.repo.TaskRepository
import com.skutilityplatforms.tasktracker.data.task_scheduler.TaskScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val taskScheduler: TaskScheduler
) :
    ViewModel() {
    private val calender = Calendar.getInstance()

    var taskTitle: MutableState<String> = mutableStateOf("")
    val taskDescription: MutableState<String> = mutableStateOf("")

    val pickedDateState: MutableState<String> = mutableStateOf("")
    val pickedTimeState: MutableState<String> = mutableStateOf("")

    val calenderState: MutableState<Calendar> = mutableStateOf(calender)

    fun onTaskTitleChanged(title: String) {
        taskTitle.value = title
    }

    fun onTaskDescriptionChanged(description: String) {
        taskDescription.value = description
    }

    fun onDatePicked() {
        pickedDateState.value =
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calender.time)
    }

    fun onTimePicked() {
        pickedTimeState.value =
            SimpleDateFormat("hh:mm aaa", Locale.getDefault()).format(calender.time)
    }

    fun saveTask(): Boolean {
        return if (taskTitle.value.isEmpty())
            false
        else if (taskDescription.value.isEmpty())
            false
        else if (pickedDateState.value.isEmpty())
            false
        else if (pickedTimeState.value.isEmpty())
            false
        else {
            viewModelScope.launch {
                val task = TaskInfo(0, taskTitle.value, taskDescription.value, calender.time)
                taskRepository.addTask(task)
                taskScheduler.scheduleTask(task)
            }
            true
        }
    }
}