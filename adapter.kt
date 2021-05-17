package com.example.easypass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val data: List<passItem>, private var listener: OnItemClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        this.listener = listener
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = data[position]
        holder.textView1.text = currentItem.platform
        holder.textView2.text = currentItem.user

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(it, position)
            }
        }

        override fun onClick(v: View) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(v, adapterPosition)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick( v: View, position: Int)
    }
}