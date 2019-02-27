package com.github.bassaer.simplemvp

class CounterPresenter(private val counterView: CounterContract.View): CounterContract.Presenter {

    private var counter = 0

    init {
        counterView.presenter = this
    }

    override fun countUp() {
        counterView.setCount(++counter)
    }

}