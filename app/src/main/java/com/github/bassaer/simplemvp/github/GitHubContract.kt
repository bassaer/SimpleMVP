package com.github.bassaer.simplemvp.github

import com.github.bassaer.simplemvp.data.source.remote.RepoInfo

interface GitHubContract {
    interface View {
        var presenter: Presenter
        fun showRepoList(list: List<RepoInfo>)
    }

    interface Presenter {
        fun loadRepoList()
    }
}
