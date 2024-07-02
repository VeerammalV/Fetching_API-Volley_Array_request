package com.example.apicallingusingvolley

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {

    private val postsLiveData = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = postsLiveData


    @SuppressLint("SuspiciousIndentation")
    suspend fun  fetchPosts(context: Context) {
        return withContext(Dispatchers.IO) {

            val apiUrl = "https://jsonplaceholder.typicode.com/posts"

            val requestQueue = Volley.newRequestQueue(context)
            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, apiUrl,null,
                { response ->
                    val posts = mutableListOf<Post>()
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val post = Post(
                            jsonObject.getInt("userId"),
                            jsonObject.getInt("id"),
                            jsonObject.getString("title"),
                            jsonObject.getString("body")
                        )
                        posts.add(post)
                    }
                    posts
                },
                {
                    error ->
                        error.printStackTrace()
                        emptyList<Post>()
                     })
            val response = requestQueue.add(jsonArrayRequest)
                response

        }
    }
}