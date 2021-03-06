package com.github.bassaer.simplemvp.data.source.remote

import io.reactivex.Observable
import retrofit2.http.GET

interface GitHubService {
    @GET("users/bassaer/repos?sort=pushed")
    fun readRepos(): Observable<List<RepoInfo>>
}