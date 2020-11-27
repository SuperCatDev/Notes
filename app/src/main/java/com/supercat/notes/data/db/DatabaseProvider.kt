package com.supercat.notes.data.db

import androidx.lifecycle.LiveData
import com.supercat.notes.data.Note

interface DatabaseProvider {
    fun observeNotes(): LiveData<List<Note>>
    fun addOrReplaceNote(newNote: Note): LiveData<Result<Note>>
}
