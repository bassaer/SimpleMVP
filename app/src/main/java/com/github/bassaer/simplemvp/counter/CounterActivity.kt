package com.github.bassaer.simplemvp.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.replaceFragmentInActivity
import com.github.bassaer.simplemvp.setupActionBar

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val counterFragment = supportFragmentManager
            .findFragmentById(R.id.contentFrame) as CounterFragment? ?:
                CounterFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
        // Create Presenter and attach View(Fragment)
        CounterPresenter(counterFragment)
    }
}
