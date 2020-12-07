package com.supercat.notes.presentation

import androidx.lifecycle.*
import com.supercat.notes.data.NotesRepository
import com.supercat.notes.model.Note
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NoteViewModel(private val notesRepository: NotesRepository, var note: Note?) : ViewModel() {
    private val showErrorLiveData = MutableLiveData<Boolean>()

    fun updateNote(text: String) {
        note = (note ?: generateNote()).copy(note = text)
    }

    fun updateTitle(text: String) {
        note = (note ?: generateNote()).copy(title = text)
    }

    fun saveNote() {
        viewModelScope.launch {
            val noteValue = note ?: return@launch

            try {
                notesRepository.addOrReplaceNote(noteValue)
            } catch (th: Throwable) {
                showErrorLiveData.value = true
            }
        }
    }

    fun showError(): LiveData<Boolean> = showErrorLiveData

    private fun generateNote(): Note {
        return Note()
    }
}

