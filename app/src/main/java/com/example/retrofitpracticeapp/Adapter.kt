package com.example.retrofitpracticeapp

import android.content.Context
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class Adapter(val context : Context, val itemList: ArrayList<ModelClass>): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val textArea : TextView = view.findViewById(R.id.textArea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userData  = itemList[position]

        holder.textArea.text = userData.title

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}