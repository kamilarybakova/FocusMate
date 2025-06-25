package com.kxmillx.focusmate.data.local.converters

import com.kxmillx.focusmate.data.local.entity.NoteEntity
import com.kxmillx.focusmate.data.local.entity.TaskEntity
import com.kxmillx.focusmate.domain.model.Note
import com.kxmillx.focusmate.domain.model.Task
import javax.inject.Inject

class TaskMapper @Inject constructor() {
    fun mapFromEntity(entity: TaskEntity): Task {
        return Task(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            isCompleted = entity.isCompleted,
            priority = entity.priority,
            dueDate = entity.dueDate,
            createdAt = entity.createdAt
        )
    }

    fun mapToEntity(domainModel: Task): TaskEntity {
        return TaskEntity(
            id = domainModel.id,
            title = domainModel.title,
            description = domainModel.description,
            isCompleted = domainModel.isCompleted,
            priority = domainModel.priority,
            dueDate = domainModel.dueDate,
            createdAt = domainModel.createdAt
        )
    }
}

class NoteMapper @Inject constructor() {
    fun mapFromEntity(entity: NoteEntity): Note {
        return Note (
            id = entity.id,
            title = entity.title,
            content = entity.content,
            createdAt = entity.createdAt
        )
    }

    fun mapToEntity(domainModel: Note): NoteEntity {
        return NoteEntity(
            id = domainModel.id,
            title = domainModel.title,
            content = domainModel.content,
            createdAt = domainModel.createdAt
        )
    }
}