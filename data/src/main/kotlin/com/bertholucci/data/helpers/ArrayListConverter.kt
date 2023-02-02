package com.bertholucci.data.helpers

import androidx.room.TypeConverter
import com.bertholucci.data.extensions.fromJson
import com.google.gson.Gson

class ArrayListConverter {

    @TypeConverter
    fun <T> fromStringArrayList(value: ArrayList<T>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun <T> toStringArrayList(value: String): ArrayList<T> {
        return try {
            Gson().fromJson(value)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}