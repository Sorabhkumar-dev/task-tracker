package com.skutilityplatforms.tasktracker.ui.screen.add_task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor() : ViewModel() {
    private val calender = Calendar.getInstance()

    var taskTitle: MutableState<String> = mutableStateOf("")
    val taskDescription: MutableState<String> = mutableStateOf("")

    val pickedDateState: MutableState<String> = mutableStateOf("")
    val pickedTimeState:MutableState<String> = mutableStateOf("")

    val calenderState :MutableState<Calendar> = mutableStateOf(calender)

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
}