package com.example.madproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.madproject.model.Character
import com.example.madproject.converters.Converters
import com.example.madproject.dao.CharacterDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        private const val DATABASE_NAME = "CHARACTER_DATABASE"
        val stat_count = 6
        val base_stat_number = 8

        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(context: Context): CharacterRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CharacterRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CharacterRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    val stats = ArrayList<Int>(stat_count)
                                    for (i in 1..stat_count) {
                                        stats.add(base_stat_number)
                                    }

                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            //create a character if none exists
                                            database.characterDao().insertCharacter(
                                                Character("Tim","Fighter",1,"Human",
                                                stats,Date())
                                            )
                                        }
                                    }
                                }
                            })

                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
