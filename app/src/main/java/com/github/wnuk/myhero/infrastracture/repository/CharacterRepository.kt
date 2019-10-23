package com.github.wnuk.myhero.infrastracture.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import com.github.wnuk.myhero.model.character.CharacterDao
import com.github.wnuk.myhero.model.character.CharacterEntity
import io.reactivex.Observable

class CharacterRepository(private val characterDao: CharacterDao) {

    val allCharacter: Observable<List<CharacterEntity>> = characterDao.getAll()

    fun getCharacter(id: String): Observable<CharacterEntity> = characterDao.loadAllByIds(id)


    suspend fun deleteAll() = characterDao.deleteAll()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: CharacterEntity) {
        characterDao.insert(character)
    }
}