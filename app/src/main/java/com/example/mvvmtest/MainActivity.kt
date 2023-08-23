package com.example.mvvmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtest.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rcv = binding.rcv
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.setHasFixedSize(true)

        val userDao = AppDatabase.getDatabase(this).userDao()
        viewModel = MainViewModel(userDao)
        binding.btnInsert.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    val id = binding.edtId.text.toString()
                    val name = binding.edtName.text.toString()
                    val age = binding.edtAge.text.toString()
                    val user = User(uid = id.toInt(), name = name, age = age)
                    userDao.insertUser(user)
                }
            }
        }

        collectLatestLifecycleFlow(viewModel.users){
            for (user in it) {
                user.run {
                    println("user: ${user.uid}")
                    println("user: ${user.name}")
                    println("user: ${user.age}")
                }
            }
            rcv.adapter = MyAdapter(it)
        }

        binding.btnGet.setOnClickListener{
            viewModel.getUser()
        }
    }

    fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T)-> Unit){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                flow.collectLatest(collect)
            }
        }
    }
}