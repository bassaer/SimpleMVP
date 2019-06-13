package com.github.bassaer.simplemvp.userlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.bassaer.simplemvp.R
import com.github.bassaer.simplemvp.counter.CounterActivity
import com.github.bassaer.simplemvp.counter.CounterFragment
import com.github.bassaer.simplemvp.data.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserlistFragment: Fragment(), UserlistContract.View {

    override lateinit var presenter: UserlistContract.Presenter

    private lateinit var userlistView: RecyclerView
    private lateinit var emptyView: TextView

    private val listAdapter = UserlistAdapter(ArrayList(0), object : UserItemListener {
        override fun onUserClick(clickedUser: User) {
            presenter.openCounter()
        }
    })

    override fun onResume() {
        super.onResume()
        presenter.loadUserlist()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.userlist_frag, container, false).apply {
            userlistView = findViewById<RecyclerView>(R.id.userlist_view).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                adapter = listAdapter
            }
            emptyView = findViewById<TextView>(R.id.empty_view).apply {
                visibility = View.GONE
            }
            requireActivity().findViewById<FloatingActionButton>(R.id.new_user_fab).apply {
                setOnClickListener {
                    presenter.addNewUser()
                }
            }
        }
    }

    override fun showCounterUI(userId: String) {
        val intent = Intent(context, CounterActivity::class.java).apply {
            putExtra(CounterFragment.ARGUMENT_USER_ID, userId)
        }
        startActivity(intent)
    }

    override fun showEmptyView() {
        emptyView.visibility = if (listAdapter.itemCount == 0) View.GONE else View.VISIBLE
    }

    override fun openGitHubRepoList() {
    }

    private class UserlistAdapter(users: List<User>, private val listener: UserItemListener) :
        RecyclerView.Adapter<UserlistAdapter.UserViewHolder>() {

        var userlist: List<User> = users
        set(users) {
            field = users
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
        }

        override fun getItemCount() = userlist.size

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            with(holder) {
                val user = userlist[position]
                nameView.text = user.name
                countView.text = user.toString()
                rootView.setOnClickListener {
                    listener.onUserClick(user)
                }
            }
        }

        class UserViewHolder(row: View): RecyclerView.ViewHolder(row) {
            val rootView: LinearLayout = row.findViewById(R.id.user_item_root)
            val nameView: TextView = row.findViewById(R.id.user_name)
            val countView: TextView = row.findViewById(R.id.user_count)
        }
    }

    interface UserItemListener {
        fun onUserClick(clickedUser: User)
    }

    companion object {
        fun newInstance() = UserlistFragment()
    }
}