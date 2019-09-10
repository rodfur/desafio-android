package com.example.desafioandroid.presentation.pulls

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioandroid.R
import com.example.desafioandroid.data.model.Pull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pull.view.*
import java.text.SimpleDateFormat

class PullsAdapter(
    private val pulls: List<Pull>,
    val onItemClickListener: ((repo: Pull) -> Unit)
) : RecyclerView.Adapter<PullsAdapter.PullsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): PullsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false)
        return PullsViewHolder(
            itemView,
            onItemClickListener
        )
    }

    override fun getItemCount() = pulls.count()

    override fun onBindViewHolder(viewHolder: PullsViewHolder, position: Int) {
        viewHolder.bindView(pulls[position])
    }

    class PullsViewHolder(
            itemView: View,
            private val onItemClickListener: ((pull: Pull) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.textTitle
        private val body = itemView.textBody
        private val created_at = itemView.textCreatedAt
        private val login = itemView.textLogin
        private val avatar = itemView.imageAvatar

        fun bindView(pull: Pull) {


            title.text = pull.title
            body.text = pull.body.take(30)
            created_at.text = pull.created_at
            login.text = pull.user.login

            Picasso.get().load(pull.user.avatar_url).into(avatar)

            itemView.setOnClickListener {
                onItemClickListener.invoke(pull)
            }
        }
    }
}