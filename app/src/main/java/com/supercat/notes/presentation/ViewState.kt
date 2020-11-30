package com.supercat.notes.presentation

import com.supercat.notes.model.Note

sealed class ViewState {
    data class Value(val notes: List<Note>) : ViewState()
    object EMPTY : ViewState()
}
