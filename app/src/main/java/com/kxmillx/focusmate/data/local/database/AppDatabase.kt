package com.kxmillx.focusmate.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.kxmillx.focusmate.data.local.dao.NoteDao
import com.kxmillx.focusmate.data.local.dao.TaskDao
import com.kxmillx.focusmate.data.local.entity.NoteEntity
import com.kxmillx.focusmate.data.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun noteDao(): NoteDao
}