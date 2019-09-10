package com.example.desafioandroid.presentation.repos

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.desafioandroid.R
import com.example.desafioandroid.data.GithubApiService
import com.example.desafioandroid.data.model.Repo
import com.example.desafioandroid.data.response.RepoBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposViewModel : ViewModel() {

    val reposLiveData: MutableLiveData<List<Repo>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getRepos(page: Int = 1) {
        GithubApiService.service.getRepos(page = page).enqueue(object : Callback<RepoBodyResponse> {
            override fun onResponse(call: Call<RepoBodyResponse>, response: Response<RepoBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val repos: MutableList<Repo> = mutableListOf()

                        response.body()?.let { repoBodyResponse ->
                            for (item in repoBodyResponse.repoItems) {
                                val repo = item.getRepoModel()
                                repos.add(repo)
                            }
                        }

                        reposLiveData.value = repos
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_REPOS, null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.repos_error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.repos_error_400_generic)
                }
            }

            override fun onFailure(call: Call<RepoBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.repos_error_500_generic)
            }
        })
    }

    companion object {
        private const val VIEW_FLIPPER_REPOS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}