package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.data.db.WaifuEntity

class Adapter(private val clickListener: IClickListener) : ListAdapter<WaifuEntity, ViewHolder>(MyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view, parent.context, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class MyDiffCallback : DiffUtil.ItemCallback<WaifuEntity>() {
    override fun areItemsTheSame(oldItem: WaifuEntity, newItem: WaifuEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WaifuEntity, newItem: WaifuEntity): Boolean {
        return oldItem == newItem
    }
}