package com.example.generalproject.Models

import com.example.generalproject.Service.IHttpModel
import java.io.Serializable

data class LoginUser(val Id: Int?=null,val name: String?=null, val password: String?=null, val yetki: Int?=null,
                @Transient    override var dataList: MutableList<LoginUser>? = mutableListOf<LoginUser>()
): IHttpModel<LoginUser>,Serializable
