package com.example.madproject.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madproject.model.Character
import com.example.madproject.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val characterRepository =
        CharacterRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val character = characterRepository.getCharacter()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    val max_level = 20
    val min_level = 1

    fun updateCharacter(name: String, c_class: String, c_level: Int, c_race: String, stats: ArrayList<Int>) {

        val newCharacter = Character(
            id = character.value?.id,
            name = name,
            c_class = c_class,
            c_level = c_level,
            c_race = c_race,
            lastUpdated = Date(),
            stats = stats
        )

        if(isCharacterValid(newCharacter)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    characterRepository.updateCharacter(newCharacter)
                }
                success.value = true
            }
        }
    }

    //function is to allow for the updating of a character via an existing Character
    fun updateCharacter(char: Character) {
        updateCharacter(char.name,char.c_class,char.c_level,char.c_race,char.stats)
    }

    private fun isCharacterValid(character: Character): Boolean {
        return when {
            character.name.isBlank() -> {
                error.value = "A name must be filled in"
                false
            }
            character.stats.isNotEmpty() -> {
                var state = true
                for (i in 1..character.stats.size) {
                    if (character.stats[i-1] > max_level || character.stats[i-1] < min_level) {
                        error.value = "A character's stats cannot be above 20 or below 1"
                        state = false
                    }
                }
                return state
            }
            character.c_level.toString().isNotEmpty() -> {
                if (character.c_level > max_level || character.c_level < min_level) {
                        error.value = "A character's level cannot be above 20 or below 1"
                    return false
                }
                true
            }
            else -> true
        }
    }
}
