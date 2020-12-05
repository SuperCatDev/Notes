package com.supercat.notes.di

import com.supercat.notes.data.NotesRepository
import com.supercat.notes.data.NotesRepositoryImpl
import com.supercat.notes.data.db.DatabaseProvider
import com.supercat.notes.data.db.FireStoreDatabaseProvider
import com.supercat.notes.model.Note
import com.supercat.notes.presentation.NoteViewModel
import com.supercat.notes.presentation.NotesViewModel
import com.supercat.notes.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

object DependencyGraph {

    private val repositoryModule by lazy {
        module {
            single { NotesRepositoryImpl(get()) } bind NotesRepository::class
            single { FireStoreDatabaseProvider() } bind DatabaseProvider::class
        }
    }

    private val viewModelModule by lazy {
        module {
            viewModel { NotesViewModel(get()) }
            viewModel { SplashViewModel(get()) }
            viewModel { (note: Note?) -> NoteViewModel(get(), note) }
        }
    }


    val modules: List<Module> = listOf(repositoryModule, viewModelModule)
}
