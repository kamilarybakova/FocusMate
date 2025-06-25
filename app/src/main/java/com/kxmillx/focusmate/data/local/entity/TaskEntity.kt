package com.kxmillx.focusmate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Entity(tableName = "tasks")
@TypeConverters(TaskPriorityConverter::class, LocalDateTimeConverter::class)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val priority: TaskPriority = TaskPriority.LOW,
    val dueDate: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class TaskPriority {
    LOW, MEDIUM, HIGH
}

class TaskPriorityConverter {
    @TypeConverter
    fun fromPriority(priority: TaskPriority): Int {
        return priority.ordinal
    }

    @TypeConverter
    fun toPriority(ordinal: Int): TaskPriority {
        return TaskPriority.values()[ordinal]
    }
}

class LocalDateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, java.time.ZoneOffset.UTC) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(java.time.ZoneOffset.UTC)
    }
}