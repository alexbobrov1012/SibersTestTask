package com.example.siberstesttask.room

import androidx.room.TypeConverter

class TypeConverters {
    private val delimiter = ", "
    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return stringList.joinToString(separator = delimiter)
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.split(delimiter)
    }
}