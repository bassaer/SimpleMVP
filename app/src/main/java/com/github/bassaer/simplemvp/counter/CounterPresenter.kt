package com.github.bassaer.simplemvp.counter

import com.github.bassaer.simplemvp.data.User
import com.github.bassaer.simplemvp.data.source.UserDataSource
import com.github.bassaer.simplemvp.data.source.local.UserRepository

class CounterPresenter(
    private val userId: String,
    private val repository: UserRepository,
    private val counterView: CounterContract.View): CounterContract.Presenter {

    private var loadedUser: User? = null

    init {
        counterView.presenter = this
    }

    override fun loadUser() {
        repository.getUser(userId, object : UserDataSource.GerUserCallback {

            override fun onUserLoaded(user: User) {
                loadedUser = user
                loadedUser?.let {
                    counterView.setText(it.count.toString())
                }
            }

            override fun onDataNotAvailable() {
                counterView.setText("Error")
            }

        })
    }

    override fun countUp() {
        loadedUser?.let {
            counterView.setText((++it.count).toString())
        }
    }

    override fun saveUser() {
        loadedUser?.let {
            repository.saveUser(user = it)
        }
    }

}