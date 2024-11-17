package com.picpay.desafio.android.ui.user.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.data.user.model.UserModel

class UserListDiffCallback(
    private val oldList: List<UserModel>,
    private val newList: List<UserModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username == newList[newItemPosition].username
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = true
}