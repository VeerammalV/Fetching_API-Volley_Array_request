package com.example.apicallingusingvolley

import PostAdapter
import PostViewModel
import ViewModelFactory
import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicallingusingvolley.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels { ViewModelFactory(PostRepository()) }
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        MyApplication.context = applicationContext
//        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        adapter = PostAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        val viewModelFactory = MyViewModelFactory(PostRepository())
//        viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)


        viewModel.posts.observe(this, Observer {posts ->
            adapter.setData(posts)
        })
        viewModel.fetchPosts(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}