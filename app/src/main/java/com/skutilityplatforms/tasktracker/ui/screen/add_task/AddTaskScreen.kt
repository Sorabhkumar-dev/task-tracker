package com.skutilityplatforms.tasktracker.ui.screen.add_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skutilityplatforms.tasktracker.R
import java.util.*

@Composable
fun AddTaskScreen(navController: NavController, viewModel: AddTaskViewModel) {
    val context = LocalContext.current
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
                    tint = MaterialTheme.colors.onSurface
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
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 80.dp, end = 16.dp)
                .clip(MaterialTheme.shapes.medium),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { showTimePicker(context, viewModel) },
                tint = MaterialTheme.colors.onSurface
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