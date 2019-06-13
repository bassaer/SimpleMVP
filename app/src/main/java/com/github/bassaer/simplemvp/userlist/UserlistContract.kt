package com.github.bassaer.simplemvp.userlist

interface UserlistContract {
    interface View {
        var presenter: Presenter
        fun showCounterUI(userId: String)
        fun openGitHubRepoList()
    }

    interface Presenter {
        fun loadUserlist()
        fun addNewUser()
        fun openCounter()
        fun deleteAllUser()
    }
}