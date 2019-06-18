package com.github.bassaer.simplemvp.github

import com.github.bassaer.simplemvp.data.source.remote.RemoteDataSource
import com.github.bassaer.simplemvp.data.source.remote.RemoteRepository
import com.github.bassaer.simplemvp.data.source.remote.RepoInfo

class GitHubPresenter(private val repository: RemoteRepository, private val repoListView: GitHubContract.View): GitHubContract.Presenter {

    init {
        repoListView.presenter = this
    }

    override fun loadRepoList() {
        repository.loadRepoList(object : RemoteDataSource.LoadRepoListCallback {
            override fun onRepoListLoaded(list: List<RepoInfo>) {
                repoListView.showRepoList(list)
            }

            override fun onDataNotAvailable() {
            }

        })
    }

}
