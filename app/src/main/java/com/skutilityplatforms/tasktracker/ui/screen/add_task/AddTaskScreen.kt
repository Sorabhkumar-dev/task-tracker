package com.skutilityplatforms.tasktracker.ui.screen.add_task

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.skutilityplatforms.tasktracker.R
import com.skutilityplatforms.tasktracker.ui.EmptyScreen
import com.skutilityplatforms.tasktracker.ui.enums.ShowScreenType
import com.skutilityplatforms.tasktracker.ui.utils.Util
import com.skutilityplatforms.tasktracker.ui.utils.isPermanentlyDenied
import java.util.*

@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddTaskViewModel,
    context: Context = LocalContext.current
) {
    var onTaskClicked by remember { mutableStateOf(false) }
    var isPermissionGranted by remember { mutableStateOf(false) }
    var showScreenType by remember { mutableStateOf(ShowScreenType.AddTaskScreen) }
    var screenText by remember { mutableStateOf(R.string.something_went_wrong) }


    if (showScreenType == ShowScreenType.AddTaskScreen) {
        AddTaskScreenContent(viewModel, context) { onTaskClicked = true }
    } else {
        EmptyScreen(modifier = Modifier.fillMaxSize(), screenText)
    }

    if (onTaskClicked) {
        TakePermissions(
            onTextChanged = { screenText = it },
            screenTypeChanged = { showScreenType = it },
            onPermissionChecked = { isPermissionGranted = true })
        if (isPermissionGranted) {
            if (!viewModel.saveTask())
                Util.showToast(context, context.getString(R.string.something_went_wrong))
            else {
                Util.showToast(
                    context,
                    context.getString(
                        R.string.task_scheduled_successfully,
                        Util.getFormattedDate(viewModel.calenderState.value.time)
                    )
                )
            }
        }
        onTaskClicked = false
    }
}

@Composable
private fun AddTaskScreenContent(
    viewModel: AddTaskViewModel,
    context: Context,
    onTaskClicked: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.add_new_task),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.h6
        )

        OutlinedTextField(
            value = viewModel.taskTitle.value,
            onValueChange = viewModel::onTaskTitleChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, end = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.enter_title),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        )

        OutlinedTextField(
            value = viewModel.taskDescription.value,
            onValueChange = viewModel::onTaskDescriptionChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, end = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.enter_description),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        )

        OutlinedTextField(
            value = viewModel.pickedDateState.value,
            onValueChange = viewModel::onTaskDescriptionChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, end = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.pick_date),
                    style = MaterialTheme.typography.subtitle2
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { showDatePicker(context, viewModel) },
                    tint = MaterialTheme.colors.secondary
                )
            }
        )

        OutlinedTextField(
            value = viewModel.pickedTimeState.value,
            onValueChange = viewModel::onTaskDescriptionChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, end = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.pick_time),
                    style = MaterialTheme.typography.subtitle2
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { showTimePicker(context, viewModel) },
                    tint = MaterialTheme.colors.secondary
                )
            }
        )

        Button(
            onClick = onTaskClicked, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 80.dp, end = 16.dp)
                .clip(MaterialTheme.shapes.medium),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )

            Text(
                text = stringResource(id = R.string.add_task),
                modifier = Modifier.padding(vertical = 4.dp),
                style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.onSecondary),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

private fun showDatePicker(context: Context, viewModel: AddTaskViewModel) {
    DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.calenderState.value[Calendar.YEAR] = year
            viewModel.calenderState.value[Calendar.MONTH] = month
            viewModel.calenderState.value[Calendar.DAY_OF_MONTH] = dayOfMonth
            viewModel.onDatePicked()
        },
        viewModel.calenderState.value[Calendar.YEAR],
        viewModel.calenderState.value[Calendar.MONTH],
        viewModel.calenderState.value[Calendar.DAY_OF_MONTH]
    ).show()
}

private fun showTimePicker(context: Context, viewModel: AddTaskViewModel) {
    TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            viewModel.calenderState.value[Calendar.HOUR] = hour
            viewModel.calenderState.value[Calendar.MINUTE] = minute
            viewModel.onTimePicked()
        },
        viewModel.calenderState.value[Calendar.HOUR],
        viewModel.calenderState.value[Calendar.MINUTE],
        false
    ).show()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun TakePermissions(
    onTextChanged: (Int) -> Unit,
    screenTypeChanged: (ShowScreenType) -> Unit,
    onPermissionChecked: (Boolean) -> Unit
) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            listOf(
                Manifest.permission.SCHEDULE_EXACT_ALARM,
                Manifest.permission.POST_NOTIFICATIONS
            )
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            listOf(Manifest.permission.SCHEDULE_EXACT_ALARM)
        else
            listOf()
    )
    if (permissionState.permissions.isEmpty()) {
        onPermissionChecked(true)
        return
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME)
                    permissionState.launchMultiplePermissionRequest()
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
    permissionState.permissions.forEach { perm ->
        when (perm.permission) {
            Manifest.permission.SCHEDULE_EXACT_ALARM -> {
                when {
                    perm.status.isGranted -> screenTypeChanged(ShowScreenType.AddTaskScreen)

                    perm.status.shouldShowRationale -> {
                        onTextChanged(R.string.please_give_alarm_permission_to_add_task)
                        screenTypeChanged(ShowScreenType.ShowPermission)
                    }

                    perm.isPermanentlyDenied() -> {
                        onTextChanged(R.string.you_permanently_denied_the_alarm_permission_please_long_press_on_app_and_go_into_setting_to_enable_it)
                        screenTypeChanged(ShowScreenType.ShowPermission)
                    }
                }
            }
            Manifest.permission.POST_NOTIFICATIONS -> {
                when {
                    perm.status.isGranted -> screenTypeChanged(ShowScreenType.AddTaskScreen)

                    perm.status.shouldShowRationale -> {
                        onTextChanged(R.string.please_give_notification_permission_to_notify_task)
                        screenTypeChanged(ShowScreenType.ShowPermission)
                    }

                    perm.isPermanentlyDenied() -> {
                        onTextChanged(R.string.you_permanently_denied_the_notification_permission_please_long_press_on_app_and_go_into_setting_to_enable_it)
                        screenTypeChanged(ShowScreenType.ShowPermission)
                    }
                }
            }
        }
        if (permissionState.permissions.all { it.status.isGranted })
            onPermissionChecked(true)
    }
}