package com.example.desafioandroid.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PullBodyResponse(

    val pullItems: List<PullDetailsResponse>
)