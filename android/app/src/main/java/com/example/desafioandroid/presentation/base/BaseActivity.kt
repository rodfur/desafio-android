package com.example.desafioandroid.presentation.base

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, title: String, showBackButton: Boolean = false) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}