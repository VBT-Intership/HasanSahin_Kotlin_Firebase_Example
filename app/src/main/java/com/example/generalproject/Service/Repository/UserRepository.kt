package com.example.generalproject.Service.Repository

import android.content.Intent
import android.widget.Toast
import com.example.generalproject.Models.LoginUser
import com.example.generalproject.Models.Post
import com.example.generalproject.Service.ApiClient
import com.example.generalproject.Service.Interfaces.UserRepo
import com.example.generalproject.UI.Activity.HomePage
import com.example.generalproject.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
class UserRepository : UserRepo {
    val fireBaseClient= ApiClient("https://dbtest-8552a.firebaseio.com/", "")
    var loginUser: LoginUser=LoginUser(0)
    override  fun userCheck(username: String, password: String) : LoginUser {
        val value = GlobalScope.async { fireBaseClient.Get<LoginUser>(LoginUser(), "User.json") { isSuccess, response, message ->
            if (isSuccess)
                if (response != null)
                    for (user in response)
                        if (user.name?.trim() == username?.trim() && user.password?.trim() == password?.trim()) {
                            loginUser=user
                            break
                        }else{
                            loginUser= LoginUser(-1)
                        }
        }}
        while (loginUser.Id==0)
            Thread.sleep(100)
        return loginUser
    }
}