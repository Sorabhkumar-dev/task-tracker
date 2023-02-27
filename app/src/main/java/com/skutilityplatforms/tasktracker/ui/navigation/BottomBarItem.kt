package com.skutilityplatforms.tasktracker.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.EventRepeat
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.ui.graphics.vector.ImageVector
import com.skutilityplatforms.tasktracker.R

sealed class BottomBarItem(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object CurrentTask : BottomBarItem(
        ScreenNavigator.CurrentTaskScreen.name,
        R.string.current_tasks,
        Icons.Outlined.PendingActions
    )

    object DailyTask : BottomBarItem(
        ScreenNavigator.DailyTaskScreen.name,
        R.string.daily_tasks,
        Icons.Outlined.EventRepeat
    )

    object Notes :
        BottomBarItem(ScreenNavigator.NoteScreen.name, R.string.notes, Icons.Outlined.EventNote)
}