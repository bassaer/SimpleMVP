package com.github.bassaer.simplemvp.data.source.local

import com.github.bassaer.simplemvp.data.User
import com.github.bassaer.simplemvp.data.source.local.UserDataSource.GerUserCallback
import com.github.bassaer.simplemvp.data.source.local.UserDataSource.LoadUserCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserLocalDataSource private constructor(private val userDao: UserDao):
    UserDataSource {

    override fun getUsers(callback: LoadUserCallback) {
        GlobalScope.launch(Dispatchers.Main) {
            val users = userDao.findAll()
            if (users.isEmpty()) {
                callback.onDataNotAvailable()
            } else {
                callback.onUserLoaded(users)
            }
        }
    }

    override fun getUser(id: String, callback: GerUserCallback) {
        GlobalScope.launch {
            val user = userDao.findById(id)
            if (user == null) {
                callback.onDataNotAvailable()
            } else {
                callback.onUserLoaded(user)
            }
        }
    }

    override fun createUser(user: User) {
        GlobalScope.launch {
            userDao.create(user)
        }
    }

    override fun saveUser(user: User) {
        GlobalScope.launch {
            userDao.save(user)
        }
    }

    override fun deleteAllUser() {
        GlobalScope.launch {
            userDao.removeAll()
        }
    }

    companion object {
        private var INSTANCE: UserLocalDataSource? = null
        fun getInstance(userDao: UserDao): UserLocalDataSource {
            return INSTANCE ?: synchronized(
                UserLocalDataSource::class.java) {
                INSTANCE
                    ?: UserLocalDataSource(userDao).also {
                        INSTANCE = it
                    }
            }
        }
    }
}