package com.skutilityplatforms.tasktracker.ui.screen.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skutilityplatforms.tasktracker.R
import com.skutilityplatforms.tasktracker.data.data_classes.TaskInfo
import com.skutilityplatforms.tasktracker.ui.EmptyScreen
import com.skutilityplatforms.tasktracker.ui.TaskCard
import com.skutilityplatforms.tasktracker.ui.navigation.ScreenNavigator
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun TasksScreen(navController: NavController, viewModel: TaskViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(ScreenNavigator.AddTaskScreen.name) }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "edit icon")
        }
    }) {
        val tasks = viewModel.tasks

        if (tasks.value.isEmpty())
            EmptyScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        else
            TaskScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), navController = navController, tasks = tasks.value
            )
    }
}

@Composable
fun TaskScreenContent(modifier: Modifier, navController: NavController, tasks: List<TaskInfo>) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.your_tasks),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                style = MaterialTheme.typography.h6
            )
        }
        items(tasks) { task ->
            TaskCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable {
                        navController.navigate(
                            ScreenNavigator.TaskDetailScreen.name.plus(
                                "/${URLEncoder.encode(
                                    "${task.taskId}",
                                    StandardCharsets.UTF_8.toString()
                                )
                            }")
                        )
                    },
                taskInfo = task
            )
        }
    }
}