package com.example.myapplication.data.db

import androidx.room.TypeConverter

class DataConverter {

    @TypeConverter
    fun booleanToInt(boolean: Boolean): Int = if (boolean) 1 else 0

    @TypeConverter
    fun intToBoolean(int: Int): Boolean = int != 0
}