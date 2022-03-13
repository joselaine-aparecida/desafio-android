package com.picpay.desafio.android.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.models.User

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            name.text = user.name
            username.text = user.username
            Glide.with(itemView)
                .load(user.img)
                .placeholder(R.drawable.picpay)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(picture)
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserListItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemUserBinding.inflate(inflater, parent, false)
            return UserListItemViewHolder(itemBinding)
        }
    }
}