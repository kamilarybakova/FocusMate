package com.kxmillx.focusmate.data.repository

import com.kxmillx.focusmate.data.local.converters.NoteMapper
import com.kxmillx.focusmate.data.local.dao.NoteDao
import com.kxmillx.focusmate.domain.model.Note
import com.kxmillx.focusmate.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteMapper: NoteMapper
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { notes ->
            notes.map { noteMapper.mapFromEntity(it) }
        }
    }

    override fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.let { noteMapper.mapFromEntity(it) }
    }

    override suspend fun addNote(note: Note) {
        return noteDao.insertNote(noteMapper.mapToEntity(note))
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.updateNote(noteMapper.mapToEntity(note))
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(noteMapper.mapToEntity(note))
    }
}