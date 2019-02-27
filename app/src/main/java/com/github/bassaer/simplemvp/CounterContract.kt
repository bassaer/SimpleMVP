package com.github.bassaer.simplemvp

interface CounterContract {
    interface View {
        var presenter: Presenter
        fun setCount(count: Int)
    }

    interface Presenter {
        fun countUp()
    }
}