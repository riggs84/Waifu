package com.example.myapplication.ui.home

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources as Resourses
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.example.myapplication.R
import com.example.myapplication.ui.Item


class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView by lazy { itemView.findViewById(R.id.imageView) }
    private val isFavorite: ImageView by lazy { itemView.findViewById(R.id.imageViewLike) }
    private val imageLoader: ImageLoader = ImageLoader.Builder(context).build()

    fun bind(item: Item) {
        image.load(item.url, imageLoader)

        when (item.isFavorite) {
            true  -> isFavorite.setImageDrawable(Resourses.getDrawable(context, R.drawable.baseline_favorite_filled))

            false -> isFavorite.setImageDrawable(Resourses.getDrawable(context, R.drawable.baseline_favorite_border_empty))
        }
    }
}