package com.kxmillx.focusmate.domain.repository

import com.kxmillx.focusmate.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun getActiveTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    fun sortByDate(): Flow<List<Task>>
    fun sortByPriority(): Flow<List<Task>>

    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}