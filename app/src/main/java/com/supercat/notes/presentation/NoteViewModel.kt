package com.supercat.notes.presentation

import androidx.lifecycle.*
import com.supercat.notes.data.NotesRepository
import com.supercat.notes.model.Note

class NoteViewModel(private val notesRepository: NotesRepository, var note: Note?) : ViewModel() {
    private val showErrorLiveData = MutableLiveData<Boolean>()

    private val lifecycleOwner: LifecycleOwner = LifecycleOwner { viewModelLifecycle }
    private val viewModelLifecycle = LifecycleRegistry(lifecycleOwner).also {
        it.currentState = Lifecycle.State.RESUMED
    }

    fun updateNote(text: String) {
        note = (note ?: generateNote()).copy(note = text)
    }

    fun updateTitle(text: String) {
        note = (note ?: generateNote()).copy(title = text)
    }

    fun saveNote() {
        note?.let { note ->
            val result = notesRepository.addOrReplaceNote(note)
            result.observe(lifecycleOwner) {
                it.onFailure {
                    showErrorLiveData.value = true
                }
            }
        }
    }

    fun showError(): LiveData<Boolean> = showErrorLiveData

    override fun onCleared() {
        super.onCleared()
        viewModelLifecycle.currentState = Lifecycle.State.DESTROYED
    }

    private fun generateNote(): Note {
        return Note()
    }
}

