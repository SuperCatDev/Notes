package com.supercat.notes.data

import com.supercat.notes.data.db.DatabaseProvider
import com.supercat.notes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.random.Random

private val idRandom = Random(0)
val noteId: Long
    get() = idRandom.nextLong()

class NotesRepositoryImpl(private val provider: DatabaseProvider) : NotesRepository {

    override suspend fun getCurrentUser() = withContext(Dispatchers.IO) {
        provider.getCurrentUser()
    }

    override fun observeNotes(): Flow<List<Note>> {
        return provider.observeNotes()
    }

    override suspend fun addOrReplaceNote(newNote: Note) = withContext(Dispatchers.IO) {
        provider.addOrReplaceNote(newNote)
    }

    override suspend fun deleteNote(noteId: String) = withContext(Dispatchers.IO) {
        provider.deleteNote(noteId)
    }
}
