package com.example.desafioandroid.data.response

import com.example.desafioandroid.data.model.Pull
import com.example.desafioandroid.data.model.Owner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.sql.Date

@JsonClass(generateAdapter = true)
data class PullDetailsResponse(

        @Json(name = "html_url")
        val url: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "title")
        val title: String,
        @Json(name = "body")
        val body: String,
        @Json(name = "created_at")
        val created_at: String,
        @Json(name = "user")
        val user: Owner

)
 {
    fun getPullModel() = Pull(

        url = this.url,
        id = this.id,
        title = this.title,
        body = this.body,
        created_at = this.created_at,
        user = this.user
    )
}