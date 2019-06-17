package com.github.bassaer.simplemvp.counter

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.bassaer.simplemvp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CounterFragment: Fragment(), CounterContract.View {

    private lateinit var textView: TextView

    override lateinit var presenter: CounterContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.counter_flag, container, false).apply {
            textView = findViewById(R.id.text)
        }
        activity?.findViewById<FloatingActionButton>(R.id.count_up_fab)?.setOnClickListener {
            presenter.countUp()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUser()
    }

    override fun onPause() {
        super.onPause()
        presenter.saveUser()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_counter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_reset -> {
                presenter.resetCount()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setText(text: String) {
        textView.text = text
    }

    companion object {
        const val ARGUMENT_USER_ID = "ARGUMENT_USER_ID"
        fun newInstance(userId: String) = CounterFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_USER_ID, userId)
            }
        }
    }
}