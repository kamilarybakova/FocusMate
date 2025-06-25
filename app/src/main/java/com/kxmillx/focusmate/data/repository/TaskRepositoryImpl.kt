package com.kxmillx.focusmate.data.repository

import com.kxmillx.focusmate.data.local.converters.TaskMapper
import com.kxmillx.focusmate.data.local.dao.TaskDao
import com.kxmillx.focusmate.domain.model.Task
import com.kxmillx.focusmate.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val taskMapper: TaskMapper
) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { tasks ->
            tasks.map { taskMapper.mapFromEntity(it) }
        }
    }

    override fun getActiveTasks(): Flow<List<Task>> {
        return taskDao.getActiveTasks().map { tasks ->
            tasks.map { taskMapper.mapFromEntity(it) }
        }
    }

    override fun getCompletedTasks(): Flow<List<Task>> {
        return taskDao.getCompletedTasks().map { tasks ->
            tasks.map { taskMapper.mapFromEntity(it) }
        }
    }

    override fun sortByDate(): Flow<List<Task>> {
        return taskDao.sortByDate().map { tasks ->
            tasks.map { taskMapper.mapFromEntity(it) }
        }
    }

    override fun sortByPriority(): Flow<List<Task>> {
        return taskDao.sortByPriority().map { tasks ->
            tasks.map { taskMapper.mapFromEntity(it) }
        }
    }

    override suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(taskMapper.mapToEntity(task))
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(taskMapper.mapToEntity(task))
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(taskMapper.mapToEntity(task))
    }
}