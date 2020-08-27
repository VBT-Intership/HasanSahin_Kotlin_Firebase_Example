package com.example.generalproject.Resources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T>ConvertStringToClassGen(data:String?):T {
    var gson = Gson()
    val turnsType = object : TypeToken<T>() {}.type
    val turns = Gson().fromJson<T>(data, turnsType)
    return turns
}