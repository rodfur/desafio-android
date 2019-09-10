package com.example.desafioandroid.presentation.repos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioandroid.R
import com.example.desafioandroid.data.model.Repo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*


class ReposAdapter(
    private val repos: List<Repo>,
    val onItemClickListener: ((repo: Repo) -> Unit)
) : RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): ReposViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ReposViewHolder(
            itemView,
            onItemClickListener
        )
    }

    override fun getItemCount() = repos.count()

    override fun onBindViewHolder(viewHolder: ReposViewHolder, position: Int) {
        viewHolder.bindView(repos[position])
    }

    class ReposViewHolder(
            itemView: View,
            private val onItemClickListener: ((repo: Repo) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.textName
        private val description = itemView.textDescription
        private val forksCount = itemView.textForksCount
        private val starsCount = itemView.textStarsCount
        private val login = itemView.textLogin
        private val avatar = itemView.imageAvatar

        fun bindView(repo: Repo) {
            name.text = repo.name
            description.text = repo.description.take(30)
            forksCount.text = repo.forks_count.toString()
            starsCount.text = repo.stargazers_count.toString()
            login.text = repo.owner.login

            Picasso.get().load(repo.owner.avatar_url).into(avatar)

            itemView.setOnClickListener {
                onItemClickListener.invoke(repo)
            }
        }
    }
}