package com.supercat.notes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.supercat.notes.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateTo(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.fragment_container_view, fragment)
            .addToBackStack("notes")
            .commit()
    }
}
