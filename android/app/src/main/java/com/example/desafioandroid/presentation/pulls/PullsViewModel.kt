package com.example.desafioandroid.presentation.pulls

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.desafioandroid.R
import com.example.desafioandroid.data.GithubApiService
import com.example.desafioandroid.data.model.Pull
import com.example.desafioandroid.data.response.PullDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullsViewModel : ViewModel() {

    val pullsLiveData: MutableLiveData<List<Pull>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getPulls(user: String, repo: String) {
        GithubApiService.service.getPulls(user, repo).enqueue(object : Callback<List<PullDetailsResponse>> {
            override fun onResponse(call: Call<List<PullDetailsResponse>>, response: Response<List<PullDetailsResponse>>) {
                when {
                    response.isSuccessful -> {
                        val pulls: MutableList<Pull> = mutableListOf()

                        response.body()?.let { pullDetailsResponse ->
                            for (item in pullDetailsResponse) {
                                val pull = item.getPullModel()
                                pulls.add(pull)
                            }
                        }

                        pullsLiveData.value = pulls
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_REPOS, null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.repos_error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.repos_error_400_generic)
                }
            }

            override fun onFailure(call: Call<List<PullDetailsResponse>>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.repos_error_500_generic)
            }
        })
    }

    companion object {
        private const val VIEW_FLIPPER_REPOS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}