package com.supercat.notes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.supercat.notes.R
import com.supercat.notes.model.Note
import com.supercat.notes.presentation.NotesViewModel
import com.supercat.notes.presentation.ViewState
import com.supercat.notes.ui.adapter.NotesAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewMode by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this).get(
            NotesViewModel::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotesAdapter {
            navigateToNote(it)
        }

        mainRecycler.adapter = adapter

        viewMode.observeViewState().observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Value -> {
                    adapter.submitList(it.notes)
                }
                ViewState.EMPTY -> Unit
            }
        }

        fab.setOnClickListener {
            navigateToCreation()
        }

        mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == SCROLL_STATE_IDLE) {
                    fab.show()
                } else {
                    fab.hide()
                }
            }
        })
    }

    private fun navigateToNote(note: Note) {
        (requireActivity() as MainActivity).navigateTo(NoteFragment.create(note))
    }

    private fun navigateToCreation() {
        (requireActivity() as MainActivity).navigateTo(NoteFragment.create(null))
    }
}
