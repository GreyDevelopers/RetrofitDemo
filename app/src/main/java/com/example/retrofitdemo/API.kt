package com.example.retrofitdemo

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @GET("getUsers.php")
    fun getUsers(): Call<User>

    @FormUrlEncoded
    @POST("insertUser.php")
    fun submitUser(
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String
    ):Call<String>

    @FormUrlEncoded
    @POST("updateUser.php")
    fun updateUser(
        @Field("id")id:String,
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String
    ):Call<String>

    @FormUrlEncoded
    @POST("getUserById.php")
    fun getUserById(
        @Field("id")id:String
    ):Call<User>
}