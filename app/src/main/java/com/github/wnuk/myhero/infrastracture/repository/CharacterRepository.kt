package com.github.wnuk.myhero.infrastracture.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.github.wnuk.myhero.model.character.CharacterDao
import com.github.wnuk.myhero.model.character.CharacterEntity
import io.reactivex.Observable

class CharacterRepository(private val characterDao: CharacterDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCharacter: Observable<List<CharacterEntity>> = characterDao.getAll()

    suspend fun deleteAll() = characterDao.deleteAll()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity) {
        characterDao.insert(character)
    }
}