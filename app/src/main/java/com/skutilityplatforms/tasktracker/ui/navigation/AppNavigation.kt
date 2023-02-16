package com.skutilityplatforms.tasktracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skutilityplatforms.tasktracker.data.utility.Constants
import com.skutilityplatforms.tasktracker.ui.screen.add_task.AddTaskScreen
import com.skutilityplatforms.tasktracker.ui.screen.add_task.AddTaskViewModel
import com.skutilityplatforms.tasktracker.ui.screen.task.TaskViewModel
import com.skutilityplatforms.tasktracker.ui.screen.task.TasksScreen
import com.skutilityplatforms.tasktracker.ui.screen.taskdetail.TaskDetailScreen
import com.skutilityplatforms.tasktracker.ui.screen.taskdetail.TaskDetailViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNavigator.TaskScreen.name) {

        composable(ScreenNavigator.TaskScreen.name) {
            val viewModel: TaskViewModel = hiltViewModel()
            TasksScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenNavigator.AddTaskScreen.name) {
            val viewModel: AddTaskViewModel = hiltViewModel()
            AddTaskScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenNavigator.TaskDetailScreen.name.plus("/${Constants.TASK_ID}")) { backStackEntry ->
            val viewModel: TaskDetailViewModel = hiltViewModel()
            TaskDetailScreen(
                navController = navController,
                viewModel = viewModel,
                taskId = backStackEntry.arguments?.getInt(Constants.TASK_ID)
            )
        }
    }
}