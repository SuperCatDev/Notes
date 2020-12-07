package com.supercat.notes.data

import com.supercat.notes.model.Note
import com.supercat.notes.model.User
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun getCurrentUser(): User?
    fun observeNotes(): Flow<List<Note>>
    suspend fun addOrReplaceNote(newNote: Note)
    suspend fun deleteNote(noteId: String)
}
