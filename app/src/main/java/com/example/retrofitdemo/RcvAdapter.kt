package com.example.retrofitdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class RcvAdapter(
    val arrayList:ArrayList<User.UserData>,
    val listener:MyItemClickListener
):RecyclerView.Adapter<RcvAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameText = itemView.findViewById<MaterialTextView>(R.id.itemName)
        val emailText = itemView.findViewById<MaterialTextView>(R.id.itemEmail)
        val editImage = itemView.findViewById<ImageView>(R.id.itemEdit)
        val deleteImage = itemView.findViewById<ImageView>(R.id.itemDelete)
        init {

            editImage.setOnClickListener {
                val position = adapterPosition
                listener.OnClick(position,true)
            }
            deleteImage.setOnClickListener {
                val position = adapterPosition
                listener.OnClick(position,false)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemList = arrayList[position]
        holder.apply {
            nameText.text = itemList.name
            emailText.text = itemList.email
        }

    }

    override fun getItemCount(): Int =arrayList.size

    interface MyItemClickListener{
        fun OnClick(position: Int,status:Boolean)
    }
}