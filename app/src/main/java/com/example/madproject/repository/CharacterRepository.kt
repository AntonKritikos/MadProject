package com.example.madproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madproject.model.Character
import com.example.madproject.database.CharacterRoomDatabase
import com.example.madproject.dao.CharacterDao

class CharacterRepository(context: Context) {
    private val characterDao: CharacterDao

    init {
        val database =
            CharacterRoomDatabase.getDatabase(context)
        characterDao = database!!.characterDao()
    }

    fun getCharacter(): LiveData<Character?> {
        return characterDao.getCharacter()
    }

    suspend fun updateCharacter(character: Character) {
        characterDao.updateCharacter(character);
    }
}