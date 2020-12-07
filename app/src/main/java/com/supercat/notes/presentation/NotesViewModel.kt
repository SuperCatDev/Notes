package com.supercat.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supercat.notes.data.NotesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NotesViewModel(notesRepository: NotesRepository) : ViewModel() {
    private val notesLiveData = MutableLiveData<ViewState>()

    init {
        notesRepository.observeNotes()
            .onEach {
                notesLiveData.value = if (it.isEmpty()) ViewState.EMPTY else ViewState.Value(it)
            }
            .launchIn(viewModelScope)
    }

    fun observeViewState(): LiveData<ViewState> = notesLiveData
}
