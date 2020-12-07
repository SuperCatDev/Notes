package com.supercat.notes.data.db

import com.supercat.notes.model.Note
import com.supercat.notes.model.User
import kotlinx.coroutines.flow.Flow

interface DatabaseProvider {
    fun getCurrentUser(): User?
    fun observeNotes(): Flow<List<Note>>
    suspend fun addOrReplaceNote(newNote: Note)
    suspend fun deleteNote(noteId: String)
}
