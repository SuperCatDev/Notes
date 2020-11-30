package com.supercat.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.supercat.notes.R
import com.supercat.notes.databinding.FragmentNoteBinding
import com.supercat.notes.model.Note
import com.supercat.notes.presentation.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteFragment : Fragment() {
    private val note: Note? by lazy(LazyThreadSafetyMode.NONE) { arguments?.getParcelable(NOTE_KEY) }

    private val viewModel by viewModel<NoteViewModel> {
        parametersOf(note)
    }

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.note?.let {
                titleEt.isVisible = true
                titleEt.setText(it.title)
                bodyEt.setText(it.note)
            }

            viewModel.showError().observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Error while saving note!", Toast.LENGTH_LONG)
                    .show()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
