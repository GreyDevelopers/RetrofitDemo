package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rcv:RecyclerView
    private lateinit var fab:FloatingActionButton
    private lateinit var constraintLayout:ConstraintLayout
    private var arrayList:ArrayList<User.UserData> = ArrayList()
    private var rcvAdapter:RcvAdapter = RcvAdapter(arrayList)
    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        retrofitClient = RetrofitClient()
        rcv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        getUser()


    }

    private fun getUser() {
        arrayList.clear()
        val call:Call<User> = retrofitClient.api.getUsers()
        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body()?.response.equals("Success")){
                    for (i in response.body()!!.data){
                        arrayList.add(i)
                    }
                    rcv.adapter = rcvAdapter
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }

    private fun initViews() {
        rcv = findViewById(R.id.rcv)
        fab = findViewById(R.id.fab)
        constraintLayout = findViewById(R.id.constraintLayout)
    }

}