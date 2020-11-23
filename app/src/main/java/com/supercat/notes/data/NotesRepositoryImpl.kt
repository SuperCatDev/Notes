package com.supercat.notes.data

import androidx.lifecycle.LiveData
import com.supercat.notes.data.db.FireStoreDatabaseProvider
import kotlin.random.Random

private val idRandom = Random(0)
val noteId: Long
    get() = idRandom.nextLong()

class NotesRepositoryImpl(val provider: FireStoreDatabaseProvider) : NotesRepository {

    override fun observeNotes(): LiveData<List<Note>> {
        return provider.observeNotes()
    }

    override fun addOrReplaceNote(newNote: Note): LiveData<Result<Note>> {
        return provider.addOrReplaceNote(newNote)
    }
}

val notesRepository: NotesRepository by lazy { NotesRepositoryImpl(FireStoreDatabaseProvider()) }
