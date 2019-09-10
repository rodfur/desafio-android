package com.example.desafioandroid.presentation.repos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.desafioandroid.R
import com.example.desafioandroid.presentation.base.BaseActivity
import com.example.desafioandroid.presentation.base.InfiniteScrollListener
import com.example.desafioandroid.presentation.pulls.PullsActivity
import kotlinx.android.synthetic.main.activity_repos.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ReposActivity : BaseActivity() {

    private var page = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        setupToolbar(toolbarMain, getString(R.string.repos_title))

        val viewModel: ReposViewModel = ViewModelProviders.of(this).get(
            ReposViewModel::class.java)

        viewModel.reposLiveData.observe(this, Observer {
            it?.let { repos ->
                with(recyclerRepos) {

                    val linearLayoutManager = LinearLayoutManager(this@ReposActivity, RecyclerView.VERTICAL, false)

                    layoutManager = linearLayoutManager
                    setHasFixedSize(true)
                    adapter =
                        ReposAdapter(repos) { repo ->
                            val intent =
                                PullsActivity.getStartIntent(
                                    this@ReposActivity,
                                    repo.name,
                                    repo.owner.login
                                )
                            this@ReposActivity.startActivity(intent)
                        }

                    addOnScrollListener(InfiniteScrollListener({ viewModel.getRepos(page++) }, linearLayoutManager))
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperRepos.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getRepos(page++)
    }
}
