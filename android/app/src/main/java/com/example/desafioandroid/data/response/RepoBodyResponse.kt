package com.example.desafioandroid.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoBodyResponse(
    @Json(name = "items")
    val repoItems: List<RepoDetailsResponse>
)