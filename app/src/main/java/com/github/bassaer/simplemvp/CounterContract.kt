package com.github.bassaer.simplemvp

interface CounterContract {
    interface View {
        var presenter: Presenter
        fun setText(text: String)
    }

    interface Presenter {
        fun countUp()
    }
}