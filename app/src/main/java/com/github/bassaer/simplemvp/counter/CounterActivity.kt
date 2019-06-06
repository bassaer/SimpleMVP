package com.github.bassaer.simplemvp.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.data.source.local.UserDatabase
import com.github.bassaer.simplemvp.data.source.local.UserLocalDataSource
import com.github.bassaer.simplemvp.data.source.local.UserRepository
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
        val userId = intent.getStringExtra(CounterFragment.ARGUMENT_USER_ID) ?: ""

        // TODO : Inject
        val userDao = UserDatabase.getInstance(this).userDao()
        val repository = UserRepository.getInstance(UserLocalDataSource.getInstance(userDao))

        CounterPresenter(userId, repository, counterFragment)
    }
}
