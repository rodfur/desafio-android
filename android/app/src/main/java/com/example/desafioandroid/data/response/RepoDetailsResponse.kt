package com.example.desafioandroid.data.response

import com.example.desafioandroid.data.model.Repo
import com.example.desafioandroid.data.model.Owner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoDetailsResponse(

        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "stargazers_count")
        val stargazers_count: Int,
        @Json(name = "forks_count")
        val forks_count: Int,
        @Json(name = "owner")
        val owner: Owner

)
 {
    fun getRepoModel() = Repo(

        id = this.id,
        name = this.name,
        description = this.description,
        stargazers_count = this.stargazers_count,
        forks_count = this.forks_count,
        owner = this.owner
    )
}