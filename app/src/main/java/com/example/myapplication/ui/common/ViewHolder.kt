package com.example.myapplication.ui.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.myapplication.R
import com.example.myapplication.data.db.WaifuEntity


class ViewHolder(itemView: View,
                 context: Context,
                 private val clickListener: IClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val imageLoader: ImageLoader = ImageLoader.Builder(context).build()

    private val image: ImageView by lazy { itemView.findViewById(R.id.imageView) }
    private val isFavorite: ImageView by lazy { itemView.findViewById(R.id.imageViewLike) }

    fun bind(item: WaifuEntity) {
        image.apply {
            load(item.url, imageLoader) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
            setOnClickListener { clickListener.onItemClicked(item.id) }
        }

        isFavorite.apply {
            setImageResource(item.icon)
            setOnClickListener { clickListener.onItemSelected(item.id) }
        }
    }
}