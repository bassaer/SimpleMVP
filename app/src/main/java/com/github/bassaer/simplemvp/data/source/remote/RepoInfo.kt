package com.github.bassaer.simplemvp.data.source.remote

import java.io.Serializable

data class RepoInfo (
    var name: String,
    var language: String,
    var star: Int
): Serializable