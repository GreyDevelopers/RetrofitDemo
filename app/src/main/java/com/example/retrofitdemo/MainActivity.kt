package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
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
        fab.setOnClickListener {
            openSubmitDialog()
        }


    }

    private fun openSubmitDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.submit_dialog,null)
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setTitle("Submit new user")
            .create()
        val dialogInstance = dialog.show()
        view.apply {
            val btn = findViewById<MaterialButton>(R.id.submitBtn)
            val name = findViewById<TextInputEditText>(R.id.nameEd)
            val email = findViewById<TextInputEditText>(R.id.emailEd)
            val password = findViewById<TextInputEditText>(R.id.passwordEd)
            btn.setOnClickListener {
                submitUser(name.text.toString(),email.text.toString(),password.text.toString())
                dialog.dismiss()
            }
        }
    }

    private fun submitUser(name: String, email: String, password: String) {
        val call:Call<String> = retrofitClient.api.submitUser(name, email, password)
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Snackbar.make(constraintLayout,"${response.body()}",Snackbar.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("TAG", "onFailure:${t.message} ")
            }
        })

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