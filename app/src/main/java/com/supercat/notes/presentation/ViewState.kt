package com.supercat.notes.presentation

import com.supercat.notes.data.Note

sealed class ViewState {
    data class Value(val notes: List<Note>) : ViewState()
    object EMPTY : ViewState()
}
