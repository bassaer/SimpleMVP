package com.github.bassaer.simplemvp.data.source.remote

import com.squareup.moshi.Json
import java.io.Serializable

data class RepoInfo (
    var name: String,
    var language: String,
    @field:Json(name = "stargazers_count")
    var star: Int
): Serializable