package com.example.generalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.generalproject.Models.LoginUser
import com.example.generalproject.Service.ApiClient
import com.example.generalproject.Service.Repository.UserRepository
import com.example.generalproject.UI.Activity.HomePage
import com.example.generalproject.UI.Fragments.WelcomeFragment
import com.example.generalproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope()  {
    private lateinit var binding: ActivityMainBinding
    val apiBaseClient=ApiClient("https://jsonplaceholder.typicode.com/", "")
    val userRepository=UserRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var welcome: WelcomeFragment = WelcomeFragment() // Fragment üzerinden sayfa alma (Widget)
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, welcome)
            .commit();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) //sayfadan veri almayı kolaylıstır

        binding.girisbtn.setOnClickListener{ view: View ->
            var username = binding.usernameText.text.toString()  //sayfadan veri alma
            var password = binding.passwordText.text.toString()
            var loginUser=userRepository.userCheck(username,password)
            if(loginUser.Id!=null){
                Toast.makeText(this,"Giriş Başarılı",Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomePage::class.java) //Safya Yonlendirme - Activity adı ile
                intent.putExtra("UserModel", loginUser) //Veri gonderme
                startActivity(intent) //Sayfa geçişini yap
            }
            else{
                Toast.makeText(this,"Giriş Başarısız",Toast.LENGTH_LONG).show()
            }

        }
    }


}



/*

 ---------------Catch-----------
catch (e:Exception){
            Log.i("main",e.toString())
        }

 System.out.println(name)  ->  print log


* */