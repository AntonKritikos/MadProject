package com.example.madproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDao {

    @Insert
    suspend fun insertCharacter(character: Character)

    @Query("SELECT * FROM character_table LIMIT 1")
    fun getCharacter(): LiveData<Character?>

    @Update
    suspend fun updateCharacter(character: Character)
}