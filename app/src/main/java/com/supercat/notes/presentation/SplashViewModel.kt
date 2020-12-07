package com.supercat.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supercat.notes.data.NotesRepository
import com.supercat.notes.errors.NoAuthException
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: NotesRepository) : ViewModel() {
    private val viewStateLiveData = MutableLiveData<SplashViewState>()

    init {
        viewModelScope.launch {
            val user = repository.getCurrentUser()

            viewStateLiveData.value = if (user != null) {
                SplashViewState.Auth
            } else {
                SplashViewState.Error(error = NoAuthException())
            }
        }
    }

    fun observeViewState(): LiveData<SplashViewState> = viewStateLiveData
}
