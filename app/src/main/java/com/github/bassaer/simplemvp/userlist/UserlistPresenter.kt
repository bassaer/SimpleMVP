package com.github.bassaer.simplemvp.userlist

import com.github.bassaer.simplemvp.data.source.local.UserRepository

class UserlistPresenter(private val repository: UserRepository, private val userlistView: UserlistContract.View)
    : UserlistContract.Presenter {


    init {
        userlistView.presenter = this
    }

    override fun loadUserlist() {

    }

    override fun addNewUser() {

    }

    override fun openCounter() {

    }

    override fun deleteAllUser() {

    }

}