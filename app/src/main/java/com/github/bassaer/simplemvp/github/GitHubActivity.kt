package com.github.bassaer.simplemvp.github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.data.source.remote.RemoteRepoDataSource
import com.github.bassaer.simplemvp.data.source.remote.RemoteRepository
import com.github.bassaer.simplemvp.replaceFragmentInActivity

class GitHubActivity: AppCompatActivity() {
    private lateinit var presenter: GitHubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.github_act)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as? GitHubFragment ?: GitHubFragment.getInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        val dataSource = RemoteRepoDataSource.getInstance()
        val repository = RemoteRepository.getInstance(dataSource)
        presenter = GitHubPresenter(repository, fragment)
    }
}