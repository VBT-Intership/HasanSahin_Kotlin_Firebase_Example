package com.example.generalproject.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generalproject.Models.LoginUser
import com.example.generalproject.Models.Post
import com.example.generalproject.R
import com.example.generalproject.UI.RecyleViews.adapter.MainAdapter
import com.example.generalproject.Service.ApiClient
import com.example.generalproject.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var adapters: RecyclerView
    lateinit var posts: MutableList<Post>
    val apiBaseClient= ApiClient("https://jsonplaceholder.typicode.com/", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)
        var User: LoginUser = intent.extras?.get("UserModel") as LoginUser// MainActivity'den gelen veriyi alma
        adapters= binding.postrecyclerView
        apiBaseClient.Get<Post>(Post(), "posts"){ isSuccess, response, message ->
            if (isSuccess) {
                runOnUiThread {
                    posts=response!!
                    adapters.adapter = MainAdapter(posts,this)
                    adapters.layoutManager = LinearLayoutManager(this)
                }
            }
        }
    }
}
