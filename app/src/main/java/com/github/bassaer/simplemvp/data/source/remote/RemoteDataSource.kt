package com.github.bassaer.simplemvp.data.source.remote

interface RemoteDataSource {
    interface LoadRepoListCallback {
        fun onRepoListLoaded(list: List<RepoInfo>)
        fun onDataNotAvailable()
    }
    fun loadRepoList(callback: LoadRepoListCallback)
}
