package com.example.madproject

import android.content.Context
import androidx.lifecycle.LiveData

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