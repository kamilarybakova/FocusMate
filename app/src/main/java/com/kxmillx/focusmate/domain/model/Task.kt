package com.kxmillx.focusmate.domain.model

import com.kxmillx.focusmate.data.local.entity.TaskPriority
import java.time.LocalDateTime

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val priority: TaskPriority = TaskPriority.LOW,
    val dueDate: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

