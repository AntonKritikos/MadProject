package com.example.madproject

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun arrayToString(array: ArrayList<Int>): String? {
        return Gson().toJson(array)
    }

    @TypeConverter
    fun stringToArray(string: String): ArrayList<Int>? {
        val itemType = object : TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson<ArrayList<Int>>(string, itemType)
    }
}
