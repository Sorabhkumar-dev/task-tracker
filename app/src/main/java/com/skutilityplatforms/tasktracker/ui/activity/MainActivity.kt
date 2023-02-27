package com.skutilityplatforms.tasktracker.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.skutilityplatforms.tasktracker.ui.screen.home.HomeScreen
import com.skutilityplatforms.tasktracker.ui.theme.TaskTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp { HomeScreen(rememberNavController()) } }
    }

    @Composable
    private fun MyApp(content: @Composable () -> Unit = {}) {
        TaskTrackerTheme {
            content()
        }
    }
}
