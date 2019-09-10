package com.example.desafioandroid.data.response

import com.example.desafioandroid.data.model.Owner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoOwnerResponse(

        @Json(name = "login")
        val login: String,
        @Json(name = "avatar_url")
        val avatar_url: String

) {
    fun getRepoOwnerModel() = Owner(

        login = this.login,
        avatar_url = this.avatar_url
    )
}