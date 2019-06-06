package com.github.bassaer.simplemvp.counter

interface CounterContract {
    interface View {
        var presenter: Presenter
        fun setText(text: String)
    }

    interface Presenter {
        fun loadUser()
        fun countUp()
        fun saveUser()
    }
}