package ru.skillbranch.devintensive.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        initToolbar()
        initViews()
        initViewModel()
    }

    private fun initToolbar() {

    }

    private fun initViews() {

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}
