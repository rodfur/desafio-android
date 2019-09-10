package com.example.desafioandroid.data

import com.example.desafioandroid.data.response.RepoBodyResponse
import com.example.desafioandroid.data.response.PullDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubServices {

    @GET("search/repositories")
    fun getRepos(
        @Query("q") q: String = "language:Java",
        @Query("page") page: Int = 1,
        @Query("sort") sort: String = "stars"
    ): Call<RepoBodyResponse>

    @GET("repos/{user}/{repo}/pulls")
    fun getPulls(
        @Path("user")user: String,
        @Path("repo")repo: String
    ): Call<List<PullDetailsResponse>>
}