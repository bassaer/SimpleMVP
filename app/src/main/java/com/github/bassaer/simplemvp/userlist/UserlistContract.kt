package com.github.bassaer.simplemvp.userlist

import com.github.bassaer.simplemvp.data.User

interface UserlistContract {
    interface View {
        var presenter: Presenter
        fun showCounterUI(userId: String)
        fun showEmptyView()
        fun showUserlist(users: List<User>)
        fun openGitHubRepoList()
    }

    interface Presenter {
        fun loadUserlist()
        fun addNewUser(user: User)
        fun openCounter(user: User)
        fun deleteAllUser()
        fun openGitHubRepoList()
    }
}