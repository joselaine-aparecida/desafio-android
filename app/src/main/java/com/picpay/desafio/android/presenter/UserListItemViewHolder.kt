package com.picpay.desafio.android.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.User

class UserListItemViewHolder(
    item: ListItemUserBinding
) : RecyclerView.ViewHolder(item.root) {
    private val text = item.name
    private val username = item.username
    private val picture = item.picture

    fun bind(user: User) {
        text.text = user.name
        username.text = user.username
        Glide.with(itemView)
            .load(user.img)
            .into(picture)
    }

    companion object {
        fun create(parent: ViewGroup): UserListItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemUserBinding.inflate(inflater, parent, false)
            return UserListItemViewHolder(itemBinding)
        }
    }
}