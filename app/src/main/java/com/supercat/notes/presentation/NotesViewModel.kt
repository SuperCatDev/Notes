package com.supercat.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.supercat.notes.data.NotesRepositoryImpl

class NotesViewModel : ViewModel() {
    private val viewStateLiveData = MutableLiveData<ViewState>(ViewState.EMPTY)

    init {
        val notes = NotesRepositoryImpl.getAllNotes()
        viewStateLiveData.value = ViewState.Value(notes)
    }

    fun observeViewState(): LiveData<ViewState> = viewStateLiveData
}
