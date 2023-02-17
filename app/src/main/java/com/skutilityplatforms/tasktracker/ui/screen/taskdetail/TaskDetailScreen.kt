package com.skutilityplatforms.tasktracker.ui.screen.taskdetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CircleNotifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.ui.utils.Util

@Composable
fun TaskDetailScreen(viewModel: TaskDetailViewModel, taskId: Int?) {
    taskId?.let {
        viewModel.getTaskInfo(it)
        viewModel.taskInfoState.value?.let { taskInfo ->
            TaskDetailScreenContent(taskInfo)
        }
    }
}

@Composable
fun TaskDetailScreenContent(taskInfo: TaskInfo) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (taskIcon, taskTitle, taskDescription, taskTiming) = createRefs()
        Icon(
            imageVector = Icons.Default.CircleNotifications,
            contentDescription = "task Icon",
            modifier = Modifier.constrainAs(taskIcon) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
                width = Dimension.value(50.dp)
                height = Dimension.value(50.dp)
            })

        Text(text = taskInfo.taskTitle, modifier = Modifier.constrainAs(taskTitle) {
            start.linkTo(taskIcon.end, 16.dp)
            top.linkTo(taskIcon.top)
            end.linkTo(parent.end, 16.dp)
            width = Dimension.fillToConstraints
        }, style = MaterialTheme.typography.subtitle1)

        Text(text = taskInfo.taskDescription, modifier = Modifier.constrainAs(taskDescription) {
            start.linkTo(taskTitle.start)
            top.linkTo(taskTitle.bottom, 16.dp)
            end.linkTo(taskTitle.end)
            width = Dimension.fillToConstraints
        }, style = MaterialTheme.typography.body1)

        Text(
            text = Util.getFormattedDate(taskInfo.taskTiming),
            modifier = Modifier.constrainAs(taskTiming) {
                start.linkTo(taskDescription.start)
                top.linkTo(taskDescription.bottom, 16.dp)
                end.linkTo(taskDescription.end)
                width = Dimension.fillToConstraints
            },
            style = MaterialTheme.typography.caption
        )

    }
}

