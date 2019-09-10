package com.example.desafioandroid.presentation.pulls

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.desafioandroid.R
import com.example.desafioandroid.presentation.base.BaseActivity
import com.example.desafioandroid.presentation.pulls.PullsActivity
import kotlinx.android.synthetic.main.activity_pulls.*
import kotlinx.android.synthetic.main.include_toolbar.*

class PullsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)

        val repo_name = intent.getStringExtra(EXTRA_NAME)
        val user = intent.getStringExtra(EXTRA_USER)

        setupToolbar(toolbarMain, repo_name)

        val viewModel: PullsViewModel = ViewModelProviders.of(this).get(
            PullsViewModel::class.java)

        viewModel.pullsLiveData.observe(this, Observer {
            it?.let { pulls ->
                with(recyclerPulls) {
                    layoutManager = LinearLayoutManager(this@PullsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter =
                        PullsAdapter(pulls) { pull ->
                            val intent = Intent(ACTION_VIEW)
                            intent.setData(Uri.parse(pull.url))
                            this@PullsActivity.startActivity(intent)
                        }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperPulls.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getPulls(user, repo_name)
    }

    companion object {
        private const val EXTRA_NAME = "EXTRA_REPO"
        private const val EXTRA_USER = "EXTRA_USER"

        fun getStartIntent(context: Context, name: String, user: String): Intent {
            return Intent(context, PullsActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_USER, user)
            }
        }
    }
}
