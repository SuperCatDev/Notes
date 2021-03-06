package com.supercat.notes.data

import androidx.lifecycle.LiveData
import com.supercat.notes.data.db.DatabaseProvider
import com.supercat.notes.model.Note
import kotlin.random.Random

private val idRandom = Random(0)
val noteId: Long
    get() = idRandom.nextLong()

class NotesRepositoryImpl(private val provider: DatabaseProvider) : NotesRepository {

    override fun getCurrentUser() = provider.getCurrentUser()

    override fun observeNotes(): LiveData<List<Note>> {
        return provider.observeNotes()
    }

    override fun addOrReplaceNote(newNote: Note): LiveData<Result<Note>> {
        return provider.addOrReplaceNote(newNote)
    }

    override fun deleteNote(noteId: String): LiveData<Result<Unit>> = provider.deleteNote(noteId)
}
