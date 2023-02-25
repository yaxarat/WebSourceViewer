package com.example.websourceviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.websourceviewer.ui.inspect.InspectFragment
import com.example.websourceviewer.ui.main.EnterUrlFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EnterUrlFragment())
                .commitNow()
        }
    }

    fun addFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, InspectFragment())
            .addToBackStack(null)
            .commit()
    }
}