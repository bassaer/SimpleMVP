package com.github.bassaer.simplemvp.counter

import com.github.bassaer.simplemvp.counter.CounterContract

class CounterPresenter(private val counterView: CounterContract.View): CounterContract.Presenter {

    private var counter = 0

    init {
        counterView.presenter = this
    }

    override fun countUp() {
        counterView.setText((++counter).toString())
    }

}