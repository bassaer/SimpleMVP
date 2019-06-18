package com.github.bassaer.simplemvp.data.source.remote

import com.squareup.moshi.Moshi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteRepoDataSource private constructor(private val service: GitHubService): RemoteDataSource {

    override fun loadRepoList(callback: RemoteDataSource.LoadRepoListCallback) {
        service.readRepos()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<RepoInfo>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onNext(repos: List<RepoInfo>) {
                    callback.onRepoListLoaded(repos)
                }
            })
    }

    companion object {
        private const val API_URL = "https://api.github.com/"
        private var INSTANCE: RemoteRepoDataSource? = null
        fun getInstance(): RemoteRepoDataSource {
            return INSTANCE ?: synchronized(RemoteRepoDataSource::class.java) {
                INSTANCE ?: RemoteRepoDataSource(getService()).also {
                    INSTANCE = it
                }
            }
        }

        private fun getService(): GitHubService {
            val moshi = Moshi.Builder().build()
            val okClient = OkHttpClient()
            val retrofit = Retrofit.Builder()
                .client(okClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(API_URL)
                .build()
            return retrofit.create(GitHubService::class.java)
        }
    }
}