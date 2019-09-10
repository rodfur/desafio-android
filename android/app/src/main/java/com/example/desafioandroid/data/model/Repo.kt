package com.example.desafioandroid.data.model

data class Repo(

    val id: Int,
    val name: String,
    val owner: Owner,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int
)
