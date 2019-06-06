package com.github.bassaer.simplemvp.data.source.local

import com.github.bassaer.simplemvp.data.User
import com.github.bassaer.simplemvp.data.source.UserDataSource

class UserRepository private constructor
    (private val userLocalDataSource: UserLocalDataSource): UserDataSource {

    override fun getUsers(callback: UserDataSource.LoadUserCallback) {
        userLocalDataSource.getUsers(object : UserDataSource.LoadUserCallback {
            override fun onUserLoaded(users: List<User>) {
                callback.onUserLoaded(users)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun getUser(id: String, callback: UserDataSource.GerUserCallback) {
        userLocalDataSource.getUser(id, object : UserDataSource.GerUserCallback {
            override fun onUserLoaded(user: User) {
                callback.onUserLoaded(user)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun createUser(user: User) {
        userLocalDataSource.createUser(user)
    }

    override fun saveUser(user: User) {
        userLocalDataSource.saveUser(user)
    }

    override fun deleteAllUser() {
        userLocalDataSource.deleteAllUser()
    }

    companion object {
        private var INSTANCE: UserRepository? = null

        fun getInstance(userLocalDataSource: UserLocalDataSource): UserRepository {
            return INSTANCE
                ?: UserRepository(userLocalDataSource).also {
                    INSTANCE = it
                }
        }
    }

}