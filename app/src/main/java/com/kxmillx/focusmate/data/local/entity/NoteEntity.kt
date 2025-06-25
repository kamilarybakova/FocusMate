package com.kxmillx.focusmate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Entity(tableName = "notes")
@TypeConverters(LocalDateTimeConverter::class)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
