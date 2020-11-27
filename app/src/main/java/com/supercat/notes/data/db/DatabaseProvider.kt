package com.supercat.notes.data.db

import androidx.lifecycle.LiveData
import com.supercat.notes.model.Note
import com.supercat.notes.model.User

interface DatabaseProvider {
    fun getCurrentUser(): User?
    fun observeNotes(): LiveData<List<Note>>
    fun addOrReplaceNote(newNote: Note): LiveData<Result<Note>>
}
