package com.example.madproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "character_table")
class Character(

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "c_class")
    var c_class: String,

    @ColumnInfo(name = "c_level")
    var c_level: Int,

    @ColumnInfo(name = "c_race")
    var c_race: String,

    @ColumnInfo(name = "stats")
    var stats: ArrayList<Int>,

    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)