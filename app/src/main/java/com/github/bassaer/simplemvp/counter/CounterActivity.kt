package com.github.bassaer.simplemvp.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.data.source.local.UserDatabase
import com.github.bassaer.simplemvp.data.source.local.UserLocalDataSource
import com.github.bassaer.simplemvp.data.source.local.UserRepository
import com.github.bassaer.simplemvp.replaceFragmentInActivity

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.counter_act)
        setSupportActionBar(findViewById(R.id.toolbar))

        val userId = intent.getStringExtra(CounterFragment.ARGUMENT_USER_ID)
        val counterFragment = supportFragmentManager
            .findFragmentById(R.id.contentFrame) as CounterFragment? ?: CounterFragment.newInstance(userId).also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        // TODO : Inject
        val userDao = UserDatabase.getInstance(this).userDao()
        val repository = UserRepository.getInstance(UserLocalDataSource.getInstance(userDao))

        CounterPresenter(userId, repository, counterFragment)
    }
}
