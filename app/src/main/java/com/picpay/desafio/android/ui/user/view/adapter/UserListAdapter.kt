package com.picpay.desafio.android.ui.user.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.data.user.model.UserModel
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.ui.user.view.adapter.viewHolder.UserListItemViewHolder

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {
    private var users = emptyList<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListItemUserBinding.inflate(inflater, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun update(list: List<UserModel>) {
        if (users != list) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(users, list)
            )
            result.dispatchUpdatesTo(this)
            users = list
        }
    }
}