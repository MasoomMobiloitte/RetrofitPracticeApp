package com.example.retrofitpracticeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val textArea : TextView = view.findViewById(R.id.textArea)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var list: List<ModelClass>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userData  = list[position]

        holder.textArea.text = userData.title

    }

    override fun getItemCount(): Int {
        return list.size
    }
}