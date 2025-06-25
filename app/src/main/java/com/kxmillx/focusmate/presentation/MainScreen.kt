package com.kxmillx.focusmate

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kxmillx.focusmate.core.BottomNavBar
import com.kxmillx.focusmate.core.Screen
import com.kxmillx.focusmate.presentation.notes.NotesScreen
import com.kxmillx.focusmate.presentation.tasks.TasksScreen
import com.kxmillx.focusmate.presentation.pomodoro.PomodoroScreen

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        Screen.Tasks, Screen.Notes, Screen.Pomodoro
    )

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, items = items)
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = Screen.Tasks.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Tasks.route) { TasksScreen() }
            composable(Screen.Notes.route) { NotesScreen() }
            composable(Screen.Pomodoro.route) { PomodoroScreen() }
        }
    }
}
