package com.kxmillx.focusmate.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kxmillx.focusmate.domain.model.Note
import com.kxmillx.focusmate.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notesState = MutableStateFlow(NotesUiState())
    val notesState: StateFlow<NotesUiState> = _notesState.asStateFlow()

    private val _noteState = MutableStateFlow(NoteUiState())
    val noteState: StateFlow<NoteUiState> = _noteState.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            repository.getAllNotes().collect { notes ->
                _notesState.value = _notesState.value.copy(
                    notes = notes,
                    isLoading = false,
                    error = null
                )
            }
        }
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            val note = repository.getNoteById(id)
            _noteState.value = _noteState.value.copy(
                note = note ?: Note(id = 0, title = "", content = "", createdAt = LocalDateTime.now()),
                isLoading = false,
                error = null
            )
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    data class NotesUiState(
        val notes: List<Note> = emptyList(),
        val isLoading: Boolean = true,
        val error: String? = null
    )

    data class NoteUiState(
        val note: Note = Note(id = 0, title = "", content = "", createdAt = LocalDateTime.now()),
        val isLoading: Boolean = true,
        val error: String? = null
    )
}
