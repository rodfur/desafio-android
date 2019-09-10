package com.example.desafioandroid.data.model

import java.sql.Date

data class Pull(

    val url: String,
    val id: Int,
    val title: String,
    val user: Owner,
    val body: String,
    val created_at: String
)
