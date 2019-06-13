package com.github.bassaer.simplemvp.userlist

import com.github.bassaer.simplemvp.data.User
import com.github.bassaer.simplemvp.data.source.UserDataSource
import com.github.bassaer.simplemvp.data.source.local.UserRepository

class UserlistPresenter(private val repository: UserRepository, private val userlistView: UserlistContract.View)
    : UserlistContract.Presenter {


    init {
        userlistView.presenter = this
    }

    override fun loadUserlist() {
        repository.getUsers(object : UserDataSource.LoadUserCallback {
            override fun onUserLoaded(users: List<User>) {

                if (users.isEmpty()) {
                    userlistView.showEmptyView()
                }


            }

            override fun onDataNotAvailable() {
            }

        })

    }

    override fun addNewUser() {

    }

    override fun openCounter(user: User) {
        userlistView.showCounterUI(user.id)
    }

    override fun deleteAllUser() {

    }

}