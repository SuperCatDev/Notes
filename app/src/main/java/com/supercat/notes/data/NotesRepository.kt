package com.supercat.notes.data

import androidx.lifecycle.LiveData
import com.supercat.notes.model.Note
import com.supercat.notes.model.User

interface NotesRepository {
    fun getCurrentUser(): User?
    fun observeNotes(): LiveData<List<Note>>
    fun addOrReplaceNote(newNote: Note): LiveData<Result<Note>>
    fun deleteNote(noteId: String): LiveData<Result<Unit>>
}
