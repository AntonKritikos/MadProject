package com.example.madlevel5task1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note_table")
class Note(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "lastUpdated")
    var lastUpdated: Date,

    @ColumnInfo(name = "text")
    var text: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)