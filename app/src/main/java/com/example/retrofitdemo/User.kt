package com.example.retrofitdemo

import java.net.CacheResponse

data class User(
    val response: String,
    val data:List<UserData>
){
    data class UserData(
        val id:String,
        val name:String,
        val email:String,
        val password:String
    )
}
