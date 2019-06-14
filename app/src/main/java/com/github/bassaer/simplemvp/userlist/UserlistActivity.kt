package com.github.bassaer.simplemvp.userlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.data.source.local.UserDatabase
import com.github.bassaer.simplemvp.data.source.local.UserLocalDataSource
import com.github.bassaer.simplemvp.data.source.local.UserRepository
import com.github.bassaer.simplemvp.replaceFragmentInActivity

class UserlistActivity: AppCompatActivity() {

    private lateinit var presenter: UserlistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userlist_act)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as UserlistFragment? ?: UserlistFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        // TODO : Inject
        val userDao = UserDatabase.getInstance(applicationContext).userDao()
        val repository = UserRepository.getInstance(UserLocalDataSource.getInstance(userDao))
        presenter = UserlistPresenter(repository, fragment)
    }
}