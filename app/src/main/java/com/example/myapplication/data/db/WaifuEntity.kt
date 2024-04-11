package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.R

@Entity(tableName = "WAIFU", indices = [Index("url", unique = true)])
data class WaifuEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val url: String,
    val isFavorite: Boolean = false,
    val icon: Int = R.drawable.baseline_favorite_border_empty
)
