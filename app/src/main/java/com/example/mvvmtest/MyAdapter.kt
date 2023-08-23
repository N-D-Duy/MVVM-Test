package com.example.mvvmtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val list: List<User>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvId.text = list[position].uid.toString()
            holder.tvName.text = list[position].name.toString()
            holder.tvAge.text = list[position].age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvId: TextView
        val tvName: TextView
        val tvAge: TextView
        init {
            tvId = itemView.findViewById(R.id.tv_id)
            tvName = itemView.findViewById(R.id.tv_name)
            tvAge = itemView.findViewById(R.id.tv_age)
        }
    }
}