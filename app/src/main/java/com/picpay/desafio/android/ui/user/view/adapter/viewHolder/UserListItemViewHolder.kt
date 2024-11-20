package com.picpay.desafio.android.ui.user.view.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.user.model.UserModel
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userModel: UserModel) {
        binding.tvName.text = userModel.name
        binding.tvUsername.text = userModel.username
        binding.pbUser.visibility = View.VISIBLE
        Picasso.get()
            .load(userModel.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.civPicture, object : Callback {
                override fun onSuccess() {
                    binding.pbUser.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.pbUser.visibility = View.GONE
                }
            })
    }
}