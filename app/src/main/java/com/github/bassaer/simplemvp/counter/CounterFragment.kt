package com.github.bassaer.simplemvp.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.bassaer.simplemvp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CounterFragment: Fragment(), CounterContract.View {

    private lateinit var textView: TextView

    override lateinit var presenter: CounterContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

    override fun setText(text: String) {
        textView.text = text
    }

    companion object {
        const val ARGUMENT_USER_ID = "ARGUMENT_USER_ID"
        fun newInstance() = CounterFragment()
    }
}