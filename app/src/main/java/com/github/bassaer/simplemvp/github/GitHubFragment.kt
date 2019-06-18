package com.github.bassaer.simplemvp.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.data.source.remote.RepoInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GitHubFragment: Fragment(), GitHubContract.View {
    override lateinit var presenter: GitHubContract.Presenter

    private lateinit var repoListView: RecyclerView
    private lateinit var emptyView: TextView

    private val listAdapter = RepoListAdapter(ArrayList(0))

    override fun showRepoList(list: List<RepoInfo>) {
        listAdapter.repoList = list
        updateView()
    }

    private fun updateView() {
        if (listAdapter.repoList.isNotEmpty()) {
            repoListView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        } else {
            repoListView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.github_frag, container, false).apply {
            repoListView = findViewById<RecyclerView>(R.id.repo_list).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                adapter = listAdapter
            }
            emptyView = findViewById(R.id.empty_view)
            requireActivity().findViewById<FloatingActionButton>(R.id.reload_button).apply {
                setOnClickListener {
                    presenter.loadRepoList()
                    updateView()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadRepoList()
    }

    private class RepoListAdapter(list: List<RepoInfo>): RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {
        var repoList: List<RepoInfo> = list
        set(repos) {
            field = repos
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false))
        }

        override fun getItemCount() = repoList.size

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
            with(holder) {
                val repo = repoList[position]
                nameView.text = repo.name
                languageView.text = repo.language
                starView.text = repo.star.toString()
            }
        }

        class RepoViewHolder(row: View): RecyclerView.ViewHolder(row) {
            val nameView: TextView = row.findViewById(R.id.repo_name)
            val languageView: TextView = row.findViewById(R.id.repo_language)
            val starView: TextView = row.findViewById(R.id.repo_stars)
        }
    }

    companion object {
        fun getInstance() = GitHubFragment()
    }
}
