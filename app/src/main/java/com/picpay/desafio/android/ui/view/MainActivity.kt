package com.picpay.desafio.android.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.util.ViewUtils.dismiss
import com.picpay.desafio.android.ui.util.ViewUtils.show
import com.picpay.desafio.android.ui.viewModel.UserViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserListAdapter

    private val viewModel: UserViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setContentView(binding.root)
        setUI()
        setObservers()
    }

    private fun setUI() {
        setAdapter()
        binding.userListProgressBar.show()
        viewModel.usersData.value ?: viewModel.getUsers()
    }

    private fun setAdapter() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        viewModel.usersData.observe(this) {
            binding.userListProgressBar.dismiss()
            adapter.update(it)
        }

        viewModel.error.observe(this) {
            binding.userListProgressBar.dismiss()
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}
