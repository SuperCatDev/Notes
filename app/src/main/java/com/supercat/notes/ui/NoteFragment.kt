package com.supercat.notes.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.supercat.notes.R
import com.supercat.notes.model.Note
import com.supercat.notes.presentation.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment(R.layout.fragment_note) {
    private val note: Note? by lazy(LazyThreadSafetyMode.NONE) { arguments?.getParcelable(NOTE_KEY) }

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NoteViewModel(note) as T
            }
        }).get(
            NoteViewModel::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.note?.let {
            titleEt.setText(it.title)
            bodyEt.setText(it.note)
        }

        viewModel.showError().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error while saving note!", Toast.LENGTH_LONG).show()
        }

        button.setOnClickListener {
            viewModel.saveNote()
        }

        toolbar.title = viewModel.note?.title ?: getString(R.string.note_creation_title)

        titleEt.addTextChangedListener {
            viewModel.updateTitle(it?.toString() ?: "")
        }

        bodyEt.addTextChangedListener {
            viewModel.updateNote(it?.toString() ?: "")
        }
    }

    companion object {
        const val NOTE_KEY = "Note"

        fun create(note: Note? = null): NoteFragment {
            val fragment = NoteFragment()
            val arguments = Bundle()
            arguments.putParcelable(NOTE_KEY, note)
            fragment.arguments = arguments

            return fragment
        }
    }
}
