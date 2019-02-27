package com.github.bassaer.simplemvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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

        CounterPresenter(counterFragment)
    }
}
