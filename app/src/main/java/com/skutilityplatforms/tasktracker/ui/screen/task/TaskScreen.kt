package com.skutilityplatforms.tasktracker.ui.screen.task

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


@Composable
fun TasksScreen(navController: NavController, viewModel: TaskViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(ScreenNavigator.AddTaskScreen.name) }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "edit icon")
        }
    }) {
        val tasks = viewModel.tasks.collectAsState(emptyList())

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
                    .padding(it), tasks = emptyList()
            )
    }
}

@Composable
fun TaskScreenContent(modifier: Modifier, tasks: List<TaskInfo>) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.your_tasks),
                style = MaterialTheme.typography.subtitle1
            )
        }
        items(tasks) { task ->
            TaskCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                taskInfo = task
            )
        }
    }
}