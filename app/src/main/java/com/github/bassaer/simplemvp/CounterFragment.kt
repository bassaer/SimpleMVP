package com.github.bassaer.simplemvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment: Fragment() {
    private var count = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.counter_flag, container, false)
        val textView: TextView = view.findViewById(R.id.text)
        val fab: View? = activity?.findViewById(R.id.fab)
        fab?.setOnClickListener {
            textView.text = (++this.count).toString()
        }
        return view
    }
}