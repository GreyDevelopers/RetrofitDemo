package com.example.retrofitdemo

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("getUsers.php")
    fun getUsers(): Call<User>
}