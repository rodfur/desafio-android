package com.example.desafioandroid.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GithubApiService {

    private fun initRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: GithubServices = initRetrofit()
        .create(GithubServices::class.java)
}