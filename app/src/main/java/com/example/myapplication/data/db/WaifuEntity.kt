package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.R

@Entity(tableName = "WAIFU")
data class WaifuEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val url: String,
    var isFavorite: Boolean = false,
    var icon: Int = R.drawable.baseline_favorite_border_empty
) {
    fun setFavoriteIcon() {
        icon = if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_empty
    }
}
