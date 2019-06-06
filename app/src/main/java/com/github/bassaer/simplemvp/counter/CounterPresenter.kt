package com.github.bassaer.simplemvp.counter

import android.util.Log
import com.github.bassaer.simplemvp.data.User
import com.github.bassaer.simplemvp.data.source.UserDataSource
import com.github.bassaer.simplemvp.data.source.local.UserRepository

class CounterPresenter(
    private val userId: String,
    private val repository: UserRepository,
    private val counterView: CounterContract.View): CounterContract.Presenter {

    private var counter = 0

    init {
        counterView.presenter = this
    }

    override fun loadUser() {
        repository.getUser(userId, object : UserDataSource.GerUserCallback {

            override fun onUserLoaded(user: User) {
                counter = user.count
                counterView.setText(counter.toString())
            }

            override fun onDataNotAvailable() {
                Log.d(javaClass.simpleName, "failed to load user.")
                counterView.setText("error")
            }

        })
    }

    override fun countUp() {
        counterView.setText((++counter).toString())
    }

}