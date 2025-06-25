package com.kxmillx.focusmate.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Tasks : Screen("tasks", "Tasks", Icons.Default.List)
    object Notes : Screen("notes", "Notes", Icons.Default.Edit)
    object Pomodoro : Screen("pomodoro", "Timer", Icons.Default.Refresh)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}
