package com.github.bassaer.simplemvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CounterFragment: Fragment(), CounterContract.View {

    private lateinit var textView: TextView

    override lateinit var presenter: CounterContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.counter_flag, container, false)
        with(root) {
            textView = findViewById(R.id.text)
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            presenter.countUp()
        }
        return root
    }

    override fun setText(text: String) {
        textView.text = text
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}