package com.github.bassaer.simplemvp.data.source.remote

class RemoteRepository private constructor(private val remoteRepoDataSource: RemoteRepoDataSource): RemoteDataSource {

    override fun loadRepoList(callback: RemoteDataSource.LoadRepoListCallback) {
        remoteRepoDataSource.loadRepoList(object : RemoteDataSource.LoadRepoListCallback {
            override fun onRepoListLoaded(list: List<RepoInfo>) {
                callback.onRepoListLoaded(list)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }


    companion object {
        const val TAG = "GitHubFragment"
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(remoteRepoDataSource: RemoteRepoDataSource) : RemoteRepository =
            INSTANCE
                ?: RemoteRepository(remoteRepoDataSource).also { INSTANCE = it }
    }

}