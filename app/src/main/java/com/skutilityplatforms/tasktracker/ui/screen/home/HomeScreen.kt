package com.skutilityplatforms.tasktracker.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.skutilityplatforms.tasktracker.ui.navigation.AppNavigation
import com.skutilityplatforms.tasktracker.ui.navigation.BottomBarItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        bottomBar = {
            BottomAppBar(navController = navController)
        }
    ) { AppNavigation(navController) }
}

@Composable
private fun BottomAppBar(navController: NavController) {
    val bottomNavItems =
        listOf(BottomBarItem.CurrentTask, BottomBarItem.DailyTask, BottomBarItem.Notes)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isShowBottomBar = bottomNavItems.any { it.route == currentDestination?.route }

    if (isShowBottomBar) {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 16.dp
        ) {
            bottomNavItems.forEach { navItem ->
                AddItem(
                    bottomBarItem = navItem,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    bottomBarItem: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == bottomBarItem.route } == true
    BottomNavigationItem(selected = isSelected,
        onClick = {
            navController.navigate(bottomBarItem.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        label = {
            Text(
                text = stringResource(id = bottomBarItem.title),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.caption.copy(
                    color = if (isSelected) MaterialTheme.colors.secondaryVariant
                    else Color.White
                )
            )
        }, icon = {
            Icon(
                imageVector = bottomBarItem.icon,
                contentDescription = "bottomBar icon",
                tint = if (isSelected) MaterialTheme.colors.secondaryVariant else Color.White
            )
        })
}
