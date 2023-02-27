package com.skutilityplatforms.tasktracker.ui.screen.notes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NoteScreen(navController: NavController,viewModel: NotesViewModel) {
    NoteScreenContent(navController = navController,viewModel = viewModel)
}

@Composable
fun NoteScreenContent(navController: NavController,viewModel: NotesViewModel) {

}