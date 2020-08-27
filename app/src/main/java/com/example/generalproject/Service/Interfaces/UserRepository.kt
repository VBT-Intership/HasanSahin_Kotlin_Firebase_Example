package com.example.generalproject.Service.Interfaces

import com.example.generalproject.Models.LoginUser

interface UserRepo {
      fun userCheck(username:String,password:String):LoginUser
}
