package com.skutilityplatforms.tasktracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skutilityplatforms.tasktracker.ui.screen.add_task.AddTaskScreen
import com.skutilityplatforms.tasktracker.ui.screen.add_task.AddTaskViewModel
import com.skutilityplatforms.tasktracker.ui.screen.daily_tasks.DailyTaskViewModel
import com.skutilityplatforms.tasktracker.ui.screen.daily_tasks.DailyTasksScreen
import com.skutilityplatforms.tasktracker.ui.screen.notes.NoteScreen
import com.skutilityplatforms.tasktracker.ui.screen.notes.NotesViewModel
import com.skutilityplatforms.tasktracker.ui.screen.task.TaskViewModel
import com.skutilityplatforms.tasktracker.ui.screen.task.TasksScreen
import com.skutilityplatforms.tasktracker.ui.screen.taskdetail.TaskDetailScreen
import com.skutilityplatforms.tasktracker.ui.screen.taskdetail.TaskDetailViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenNavigator.CurrentTaskScreen.name
    ) {

        composable(ScreenNavigator.CurrentTaskScreen.name) {
            val viewModel: TaskViewModel = hiltViewModel()
            TasksScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenNavigator.DailyTaskScreen.name) {
            val viewModel: DailyTaskViewModel = hiltViewModel()
            DailyTasksScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenNavigator.NoteScreen.name) {
            val viewModel: NotesViewModel = hiltViewModel()
            NoteScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenNavigator.AddTaskScreen.name) {
            val viewModel: AddTaskViewModel = hiltViewModel()
            AddTaskScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenNavigator.TaskDetailScreen.name.plus("/taskId")) { backStackEntry ->
            val viewModel: TaskDetailViewModel = hiltViewModel()
            TaskDetailScreen(
                viewModel = viewModel,
                taskId = backStackEntry.arguments?.getString("taskId")?.toInt()
            )
        }
    }
}